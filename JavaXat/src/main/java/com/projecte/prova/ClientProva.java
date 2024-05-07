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
        try {
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
        } finally {
            // Cerrar conexiones
//            if (socket != null) {
//                socket.close();
//            }
//            if (in != null) {
//                in.close();
//            }
//            if (out != null) {
//                out.close();
//            }
        }
    }
    
    public void enviarMissatge(String text){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        // Cerrar conexiones
        if (socket != null) {
            socket.close();
        }
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.close();
        }
    }
    
    public static String getNomUsuari() {
        return usuari;
    }
}

/*import java.io.*;
import java.net.*;

public class ClientProva {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVIDOR_IP, PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            Thread entradaMissatges = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String missatgeDelServidor;
                        while ((missatgeDelServidor = in.readLine()) != null) {
                            System.out.println(missatgeDelServidor);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            entradaMissatges.start();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String userInputLine;
            while ((userInputLine = userInput.readLine()) != null) {
                out.println(userInputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/