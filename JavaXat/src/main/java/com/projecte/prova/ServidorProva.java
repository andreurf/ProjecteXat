package com.projecte.prova;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date; // Importa la classe Date

public class ServidorProva {

    private static final int PORT = 7878;
    private static String grup = "DAM"; // Nom del grup fixe
    private static final HashSet<PrintWriter> clients = new HashSet<>();
    private static final MongoServeis dbManager = new MongoServeis();
    private static final ArrayList<Usuari> usuaris = new ArrayList<>();
    private static Usuari user;
    private static boolean missPrivat = false;
    private static final HashMap<String, PrintWriter> clientesPorNombre = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor del xat en línia...");
            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

//                out.println("Entra el teu nom d'usuari:");
                out.println("OK");
                String nom = in.readLine();
                clientesPorNombre.put(nom, out);
//                out.println("Entra la teva contrasenya:"); // Modificat: demana la contrasenya
                System.out.println(nom + " s'ha connectat al grup " + grup);
                Usuari usuari = new Usuari(nom, socket, true);
                usuaris.add(usuari);
                user = usuari;

                new ComprovarEstatClient(usuaris, user).start();

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
        private PrintWriter out;
        private String nomReceptor;

        public Handler(Socket socket, String nom) {
            this.socket = socket;
            this.nom = nom;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                clients.add(out);

                // Atendre les peticions del client
                String missatge;
                while (true) {
                    System.out.println("Rebent missatge...");
                    missatge = in.readLine();
                    System.out.println(missatge);
                    if (missatge == null) {
                        return;
                    }
                    if (missatge.startsWith("/p")) {
                        String[] partes = missatge.split(" ", 3);
                        String nomReceptor = partes[1];
                        String mensajePrivado = partes[2];

                        PrintWriter destinatario = clientesPorNombre.get(nomReceptor);
                        if (destinatario != null) {
                            // Enviar el mensaje privado al destinatarioç
                            System.out.println("/pm " + nom + " (Mensaje privado): " + mensajePrivado);
                            destinatario.println(nom);                            
                            destinatario.println(mensajePrivado);
                        } else {
                            // Si el destinatario no existe, informar al remitente
                            out.println("El usuario " + nomReceptor + " no está conectado o no existe.");
                        }
                        continue; // Saltar al siguiente mensaje
                    }

                    Missatge missatgeModel = new Missatge(nom, missatge, new Date(), grup);
                    dbManager.desarMissatge(missatgeModel); // Desa el missatge a la base de dades
                    new RealitzarEnviaments(usuaris, missatge, nom).start();
                }
            } catch (IOException e) {
                System.out.println("Error en la connexió amb el client: " + e.getMessage());
            } finally {
                // Desconexió usuari
                clients.remove(out); // Eliminar el PrintWriter del client desconectat de la lista de clients
                try {
                    socket.close(); // Tancar el socket
                    System.out.println(nom + " s'ha desconectat");
                    // Notificar als altres clients sobre la desconexió
                    for (PrintWriter client : clients) {
                        client.println(nom);
                        client.println(" s'ha desconectat");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class RealitzarEnviaments extends Thread {

        private final ArrayList<Usuari> usuaris;
        private PrintWriter out;
        private final String missatge;
        private final String nom;

        public RealitzarEnviaments(ArrayList<Usuari> usuaris, String missatge, String nom) {
            this.usuaris = usuaris;
            this.missatge = missatge;
            this.nom = nom;
        }

        @Override
        public void run() {
            try {
                for (Usuari usu : usuaris) {
                    // Obtindre el socket del usuari
                    Socket socket = usu.getSocket();
                    out = new PrintWriter(socket.getOutputStream(), true);
                    System.out.println("Enviant missatge...");
                    //Enviar missatge als usuaris que es conecten
                    out.println(nom);
                    out.println(missatge);
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static class ComprovarEstatClient extends Thread {

        private final ArrayList<Usuari> usuaris;
        private final Usuari usuari;

        public ComprovarEstatClient(ArrayList<Usuari> usuaris, Usuari usuari) {
            this.usuaris = usuaris;
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