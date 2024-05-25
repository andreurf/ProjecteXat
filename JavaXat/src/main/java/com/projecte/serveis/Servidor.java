package com.projecte.serveis;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import java.util.Base64;

public class Servidor {

    private static final int PORT = 7878;
    private static final MongoServeis dbManager = MongoServeis.getInstance();
    private static final ArrayList<Usuari> usuaris = new ArrayList<>();
    private static SecretKey clauAES; // Clau AES compartida
    private static KeyPair serverClauRSA; // Claus RSA del servidor
    private static PublicKey clientClauPublica; // Clau pública del client

    public static void main(String[] args) {
        try {
            // Generar claus RSA del servidor
            KeyPairGenerator clauRSA = KeyPairGenerator.getInstance("RSA");
            clauRSA.initialize(2048);
            serverClauRSA = clauRSA.genKeyPair();

            // Generar clau AES compartida
            KeyGenerator novaClauAES = KeyGenerator.getInstance("AES");
            novaClauAES.init(256);
            clauAES = novaClauAES.generateKey();
            
            // Obtindre la IP local per al servidor
            InetAddress localIP = InetAddress.getLocalHost();
            
            ServerSocket serverSocket = new ServerSocket(PORT, 0, localIP);
            System.out.println("Servidor del xat en línia..." + localIP.getHostAddress() + ":" + PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                boolean usuRepetit = false;

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Guardar credencials
                String nom = in.readLine();
                System.out.println(nom + " s'ha connectat");
                if (!usuaris.isEmpty()) {
                    for (Usuari usu : usuaris) {
                        if (nom.equals(usu.getNomUsuari())) {
                            usuRepetit = true;
                            break;
                        }
                    }
                }

                if (!usuRepetit) {
                    Usuari usuari = new Usuari(nom, socket);
                    usuaris.add(usuari);

                    // Enviar que pot iniciar sessió
                    out.println("OK");

                    // Obtenir clau pública del client
                    byte[] byteKey = Base64.getDecoder().decode(in.readLine().trim());
                    X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
                    KeyFactory kf = KeyFactory.getInstance("RSA");
                    clientClauPublica = kf.generatePublic(X509publicKey);

                    // Xifrar clau AES amb clau pública del client i enviar-li
                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.ENCRYPT_MODE, clientClauPublica);
                    byte[] encryptedKey = cipher.doFinal(clauAES.getEncoded());
                    String encryptedAESKey = Base64.getEncoder().encodeToString(encryptedKey);
                    out.println(encryptedAESKey);
                    
                    // Generar fil per comprovar conexions
                    new ComprovarEstatClient(usuari).start();
                    // Generar fil per rebre missatge
                    new Handler(socket, nom).start();
                } else {
                    out.println("Repetit");
                    System.out.println("Usuari ja loguejat");
                }
            }
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
    
    public static class ComprovarEstatClient extends Thread {

        private final Usuari usuari;

        public ComprovarEstatClient(Usuari usuari) {
            this.usuari = usuari;
        }

        @Override
        public void run() {
            try {
                for (Usuari user : usuaris) {
                    PrintWriter out = new PrintWriter(user.getSocket().getOutputStream(), true);
                    if (usuari.getNomUsuari().equals(user.getNomUsuari())) {
                        // Enviar la llista d'usuaris ja conectats al nou usuari
                        for (Usuari u : usuaris) {
                            if (!u.getNomUsuari().equals(usuari.getNomUsuari())) {
                                out.println(u.getNomUsuari());
                                out.println(encriptarMissatge(" s'ha unit al xat"));
                            }
                        }
                    } else {
                        // Notificar als usuaris ja conectats sobre el nou client
                        out.println(usuari.getNomUsuari());
                        out.println(encriptarMissatge(" s'ha unit al xat"));
                    }
                }
                System.out.println(usuari.getNomUsuari() + " s'ha unit");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Desencriptar missatge amb AES
    private static String desencriptarMissatge(String encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, clauAES);
        byte[] bytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(bytes);
        return new String(decryptedBytes, "UTF-8");
    }

    // Encriptar missatge amb AES
    private static String encriptarMissatge(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, clauAES);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);

    }

    private static class Handler extends Thread {

        private final String nom;
        private final Socket socket;
        private BufferedReader in;

        public Handler(Socket socket, String nom) {
            this.socket = socket;
            this.nom = nom;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Atendre les peticions del client
                String missatgeEncriptat;
                while (true) {
                    missatgeEncriptat = in.readLine().trim();
                    System.out.println("Rebent missatge encriptat: " + missatgeEncriptat);
                    if (missatgeEncriptat == null) {
                        return;
                    }

                    // Desencriptar el missatge
                    String missatge = desencriptarMissatge(missatgeEncriptat);
                    System.out.println("Missatge desencriptat: " + missatge);
                    
                    if (missatge.startsWith("/w")) {
                        // Rebre Missatge per saber quin xat observa l'usuari
                        String[] missObservar = missatge.split(" ", 3);
                        String usuariObservant = missObservar[1];
                        String xatObservat = missObservar[2];
                        for (Usuari usu : usuaris) {
                            if (usu.getNomUsuari().equals(usuariObservant)) {
                                usu.setReceptor(xatObservat);
                                System.out.println("Usuari " + usuariObservant + " visualitzant xat: " + xatObservat);
                            }
                        }
                    } else if (missatge.startsWith("/p")) {
                        // Rebre Missatge privat
                        String[] parts = missatge.split(" ", 3);
                        String xatObservat = parts[1];
                        String missatgePrivat = parts[2];
                        for (Usuari usu : usuaris) {
                            if (nom.equals(usu.getNomUsuari())) {
                                System.out.println("/p " + nom + ": " + missatgePrivat + " a " + xatObservat);
                                guardarMissatge(nom, missatgePrivat, xatObservat, true);
                            }
                        }
                    } else {
                        // Rebre Missatge del grup
                        guardarMissatge(nom, missatge, "DAM", false);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error en la connexió amb el client: " + e.getMessage());
            } finally {
                // Quan el client es desconecta
                synchronized (usuaris) {
                    Iterator<Usuari> iterator = usuaris.iterator();
                    while (iterator.hasNext()) {
                        Usuari usu = iterator.next();
                        if (usu.getSocket() == socket) {
                            iterator.remove();
                            break;
                        }
                    }
                }
                try {
                    socket.close(); // Tancar el socket
                    System.out.println(nom + " s'ha desconnectat");
                    // Notificar els altres clients sobre qui s'ha desconectat
                    String missDescon = " s'ha desconnectat";
                    new RealitzarEnviaments(missDescon, nom, false, "DAM").start();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void guardarMissatge(String nom, String missatge, String grup, boolean missPrivat) {
        Missatge missatgeModel = new Missatge(nom, missatge, new Date(), grup);
        dbManager.desarMissatge(missatgeModel); // Desa el missatge a la base de dades
        // Generar fil per rebre missatges
        new RealitzarEnviaments(missatge, nom, missPrivat, grup).start();

    }

    public static class RealitzarEnviaments extends Thread {

        private final String missatge;
        private final String nom;
        private final boolean missPrivat;
        private final String nomR;

        public RealitzarEnviaments(String missatge, String nom, boolean missPrivat, String nomR) {
            this.missatge = missatge;
            this.nom = nom;
            this.missPrivat = missPrivat;
            this.nomR = nomR;
        }

        @Override
        public void run() {
            try {
                String missatgeEncriptat = encriptarMissatge(missatge);
                if (missPrivat) {
                    // Enviar missatge privat
                    for (Usuari usu : usuaris) {
                        if ((usu.getNomUsuari().equals(nom) || usu.getNomUsuari().equals(nomR)) && (usu.getReceptor().equals(nom) || usu.getReceptor().equals(nomR))) {
                            // Usuari rebra el missatge en cas de que observi el xat de qui li envia el missatge se li envia
                            Socket socket = usu.getSocket();
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Enviant missatge privat a " + usu.getNomUsuari());
                            // Enviar missatge als usuaris connectats
                            out.println(nom);
                            out.println(missatgeEncriptat);
                            out.flush();
                        }
                    }
                } else {
                    // Enviar missatge grup
                    System.out.println("Enviant missatge a tots els usuaris del grup DAM");
                    for (Usuari usu : usuaris) {
                        if (usu.getReceptor().equals("DAM")) {
                            // Si l'usuari no esta mirant el grup el missatge no se li envia
                            Socket socket = usu.getSocket();
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            // Enviar missatge als usuaris connectats
                            out.println(nom);
                            out.println(missatgeEncriptat);
                            out.flush();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
