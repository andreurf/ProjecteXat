package com.projecte.prova;

import java.io.*;
import java.net.*;
import java.util.Date;

public class ServerProva {

    private static final int PORT = 7878;
    private static String grup = "DAM";
    private static MongoDBManager dbManager = new MongoDBManager();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor del xat en línea...");
            while (true) {
                Socket socket = serverSocket.accept();
                new RebreMissatgesS(socket).start();
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }

    private static class RebreMissatgesS extends Thread {

        private String nom;
        private String contrasenya;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public RebreMissatgesS(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("OK");
                nom = in.readLine();

                contrasenya = in.readLine();
                System.out.println(nom + " s'ha connectat al grup " + grup);

                UsuariModelProva usuari = new UsuariModelProva(nom, contrasenya, new Date(), grup);
                dbManager.desarUsuari(usuari);

                while (true) {
                    String missatge = in.readLine();
                    if (missatge == null) {
                        break;
                    }
                    MissatgeModelProva missatgeModel = new MissatgeModelProva(nom, missatge, new Date(), grup);
                    dbManager.desarMissatge(missatgeModel);
                }
            } catch (IOException io) {
                System.out.println(io);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("Error cerrando el socket: " + e.getMessage());
                }
            }
        }
    }
}
