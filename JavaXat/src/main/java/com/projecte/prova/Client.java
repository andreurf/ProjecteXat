package com.projecte.prova;

import com.projecte.swing.ChatBody;
import com.projecte.swing.ChatTitol;
import java.io.*;
import java.net.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.SwingUtilities;

public class Client {

    private final String serverIP;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private static String nomUsuari;
    private SecretKey clauAES;
    private static KeyPair clientClauRSA; // Claus RSA del client

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public boolean obtenirUsuari(String nomUsu) {
        try {
            System.out.println("/////////////////////////" + nomUsu + "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
            // Generar claus RSA del client
            KeyPairGenerator clauRSA = KeyPairGenerator.getInstance("RSA");
            clauRSA.initialize(2048);
            clientClauRSA = clauRSA.genKeyPair();

            // Conectar al servidor
            socket = new Socket(serverIP, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            nomUsuari = nomUsu;

            // Esperar resposta del servidor
            String resposta = in.readLine();
            if (!resposta.equals("OK")) {
                return false;
            }

            // Enviar credencials al servidor
            out.println(nomUsuari);

            // Enviar clau pública al servidor
            out.println(Base64.getEncoder().encodeToString(clientClauRSA.getPublic().getEncoded()));

            // Rebre clau AES xifrada
            String clauAESencriptada = in.readLine().trim();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, clientClauRSA.getPrivate());
            byte[] decodedKey = Base64.getDecoder().decode(clauAESencriptada);
            byte[] decryptedKey = cipher.doFinal(decodedKey);
            clauAES = new SecretKeySpec(decryptedKey, 0, decryptedKey.length, "AES");

            // Si la resposta del servidor és "OK", l'inici de sessió és exitós
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public void enviarMissatge(String text) {
        try {
            String missatgeEncriptat = encriptarMissatge(text, clauAES);
            out.println(missatgeEncriptat);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String encriptarMissatge(String message, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public void iniciarReceptorMissatges(ChatBody chatBody, ChatTitol chatTitol) {
        new Thread(() -> {
            try {
                while (true) {
                    String nom = in.readLine().trim();
                    String missatgeEncriptat = in.readLine().trim();
                    System.out.println(nom + " (Missatge Encriptat): " + missatgeEncriptat);
                    if (nom != null && missatgeEncriptat != null) {
                        String missatge = desencriptarMissatge(missatgeEncriptat, clauAES);
                        String time = new SimpleDateFormat("HH:mm").format(new Date());
                        System.out.println(" (Missatge Desencriptat): " + missatge);
                        if (missatge.equals(" s'ha unit al xat") || missatge.equals(" s'ha desconnectat")) {
                            SwingUtilities.invokeLater(() -> chatBody.addEstat(nom + missatge));
                        } else {
                            mostrarMissatge(nom, missatge, chatBody, time);
                        }
                        chatBody.revalidate();
                        chatBody.repaint();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private String desencriptarMissatge(String encryptedMessage, SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedMessage);
        return new String(decryptedBytes, "UTF-8");
    }

    public static void mostrarMissatge(String nom, String missatge, ChatBody chatBody, String time) {
        System.out.println(nom + ": " + missatge);
        if (nom.equals(nomUsuari)) {
            SwingUtilities.invokeLater(() -> chatBody.addItemD(missatge, time));
        } else {
            SwingUtilities.invokeLater(() -> chatBody.addItemE(missatge, nom, time));
        }
    }

    public static String getNomUsuari() {
        return nomUsuari;
    }
}
