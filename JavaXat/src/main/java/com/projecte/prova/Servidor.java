package com.projecte.prova;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date; // Importa la classe Date

public class Servidor {

    private static final int PORT = 7878;
    private static final MongoServeis dbManager = new MongoServeis();
    private static final ArrayList<Usuari> usuaris = new ArrayList<>();
    private static Usuari user;
    private static final boolean missPrivat = false;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor del xat en línia...");
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                out.println("OK");
                String nom = in.readLine();

                System.out.println(nom + " s'ha connectat");
                Usuari usuari = new Usuari(nom, socket, true);
                usuaris.add(usuari);
                user = usuari;

                new ComprovarEstatClient(user).start();

                new Handler(socket, nom).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
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
                String missatge;
                while (true) {
                    missatge = in.readLine();
                    System.out.println("Rebent missatge: "+missatge);
                    if (missatge == null) {
                        return;
                    }
                    if (missatge.startsWith("/w")) {
                        String[] missObservar = missatge.split(" ", 3);
                        String nomObservador = missObservar[1];
                        String nomObservat = missObservar[2];
                        for (Usuari usu : usuaris) {
                            if (usu.getNomUsuari().equals(nomObservador)) {
                                usu.setReceptor(nomObservat);
                                System.out.println("Usuari " + nomObservador + " visualitzant xat: " + nomObservat);
                            }
                        }
                    } else if (missatge.startsWith("/p")) {
                        String[] partes = missatge.split(" ", 3);
                        String nomObservat = partes[1];
                        String missatgePrivat = partes[2];
                        for (Usuari usu : usuaris) {
                            if (nom.equals(usu.getNomUsuari())) {
                                if (nomObservat.equals(usu.getReceptor())) {
                                    System.out.println("/p " + nom + ": " + missatgePrivat + " to " + nomObservat);
                                    guardarMissatge(nom, missatgePrivat, nomObservat, true);
                                }
                            }
                        }
                    } else {
                        guardarMissatge(nom, missatge, "DAM", false);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error en la connexió amb el client: " + e.getMessage());
            } finally {
                for (Usuari usu : usuaris) {
                    if (usu.getSocket() == socket) {
                        usuaris.remove(usu);
                    }
                }
                try {
                    socket.close(); // Tancar el socket
                    System.out.println(nom + " s'ha desconectat");
                    // Notificar als altres clients sobre la desconexió
                    String missDescon = nom + " s'ha desconectat";
                    new RealitzarEnviaments(missDescon, nom, false, "DAM").start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void guardarMissatge(String nom, String missatge, String grup, boolean missPrivat) {
        Missatge missatgeModel = new Missatge(nom, missatge, new Date(), grup);
        dbManager.desarMissatge(missatgeModel); // Desa el missatge a la base de dades
        new RealitzarEnviaments(missatge, nom, missPrivat, grup).start();
    }

    public static class RealitzarEnviaments extends Thread {
        
        private final String missatge;
        private final String nom;
        private final boolean missPrivat;
        private String nomR;

        public RealitzarEnviaments(String missatge, String nom, boolean missPrivat, String nomR) {
            this.missatge = missatge;
            this.nom = nom;
            this.missPrivat = missPrivat;
            this.nomR = nomR;
        }

        @Override
        public void run() {
            try {
                if (missPrivat) {
                    for (Usuari usu : usuaris) {
                        if (usu.getNomUsuari().equals(nom)) {
                            // Obtindre el socket del usuari
                            Socket socket = usu.getSocket();
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Enviant missatge privat...");
                            //Enviar missatge als usuaris que es conecten
                            out.println(nom);
                            out.println(missatge);
                            out.flush();
                        } else if (usu.getNomUsuari().equals(nomR)) {
//                            System.out.println(usu.getNomUsuari() + " observant " + usu.getReceptor());
                            if (usu.getReceptor().equals(nom)) {
                                // Obtindre el socket del usuari
                                Socket socket = usu.getSocket();
                                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                                System.out.println("Enviant missatge privat 2...");
                                //Enviar missatge als usuaris que es conecten
                                out.println(nom);
                                out.println(missatge);
                                out.flush();
                            }
                        }
                    }
                } else {
                    for (Usuari usu : usuaris) {
                        if (usu.getReceptor().equals("DAM")) {
                            // Obtindre el socket del usuari
                            Socket socket = usu.getSocket();
                            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                            System.out.println("Enviant missatge...");
                            //Enviar missatge als usuaris que es conecten
                            out.println(nom);
                            out.println(missatge);
                            out.flush();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static class ComprovarEstatClient extends Thread {
        
        private final Usuari usuari;

        public ComprovarEstatClient( Usuari usuari) {
            this.usuari = usuari;
        }

        @Override
        public void run() {
            try {
                for (Usuari user : usuaris) {
                    if (!user.equals(usuari)) {
                        PrintWriter out = new PrintWriter(user.getSocket().getOutputStream(), true);
                        out.println(usuari.getNomUsuari());
                        out.println(" s'ha unit al xat");
                    }
                }
                System.out.println(usuari.getNomUsuari() + " s'ha unit");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
