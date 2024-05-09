package com.projecte.prova;

import java.io.*;
import java.net.*;

public class ClientProva {

    private String serverIP;
    private int serverPort;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private static String usuari;
    private static String text;

    public ClientProva(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public boolean iniciarSesion(String usuari, String contrasenya) throws IOException {
            socket = new Socket(serverIP, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Enviar credenciales al servidor
            out.println(usuari);
            this.usuari = usuari;
            out.println(contrasenya);

            // Esperar respuesta del servidor
            String resposta = in.readLine();

            // Si el servidor responde con "OK", el inicio de sesión es exitoso
            return resposta.equals("OK");
    }

    public void enviarMissatge(String text) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String rebreMissatges() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNomUsuari() {
        return usuari;
    }
}
