package com.projecte.prova;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date; // Importa la classe Date

public class ServerProva {

    private static final int PORT = 7878;
    private static String grup = "DAM"; // Nom del grup fixe
    private static HashSet<PrintWriter> clients = new HashSet<>();
    private static MongoDBManager dbManager = new MongoDBManager();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor del xat en línia...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static class Handler extends Thread {

        private String nom;
        private String contrasenya; // Afegit: guarda la contrasenya
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

//                out.println("Entra el teu nom d'usuari:");
                out.println("OK");
                nom = in.readLine();

//                out.println("Entra la teva contrasenya:"); // Modificat: demana la contrasenya
                contrasenya = in.readLine(); // Modificat: llegeix la contrasenya
                System.out.println(nom + " s'ha connectat al grup " + grup);

                UsuariModelProva usuari = new UsuariModelProva(nom, contrasenya, new Date(), grup);
                dbManager.desarUsuari(usuari);// Desa el missatge a la base de dades

                synchronized (clients) {
                    clients.add(out);
                }

                // Atendre les peticions del client
                String missatge;
                while (true) {
                    missatge = in.readLine();
                    if (missatge == null) {
                        return;
                    }
                    MissatgeModelProva missatgeModel = new MissatgeModelProva(nom, missatge, new Date(), grup);
                    dbManager.desarMissatge(missatgeModel); // Desa el missatge a la base de dades

                    // Enviar el missatge als clients
                    synchronized (clients) {
                        for (PrintWriter client : clients) {
                            if (client != out) { // Si el client no és el mateix que l'envia
                                client.println(nom + ": " + missatge); // Mostra el nom de l'usuari que envia el missatge
                            } else {
                                client.println("Jo: " + missatge); // Al client que envia el missatge, mostra "Jo"
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error en la connexió amb el client: " + e.getMessage());
            } finally {
                if (nom != null) {
                    clients.remove(out);
                    synchronized (clients) {
                        // Enviem un missatge de desconnexió als clients del mateix grup
                        for (PrintWriter client : clients) {
                            client.println(nom + " s'ha desconnectat del grup " + grup);
                        }
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
