package com.projecte.prova;

import java.io.*;
import java.net.*;

public class Client {
    private final String serverIP;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private static Usuari usuari;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public boolean obtindreUsuari(String nomUsuari) throws IOException {
            socket = new Socket(serverIP, serverPort);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            usuari = new Usuari(nomUsuari);
            
            // Enviar credenciales al servidor
            out.println(nomUsuari);

            // Esperar respuesta del servidor
            String resposta = in.readLine();
            
            // Si el servidor responde con "OK", el inicio de sesión es exitoso
            return resposta.equals("OK");
    }
    
    public void enviarMissatge(String text){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getNomUsuari() {
        return usuari.getNomUsuari();
    }
}

/*import java.io.*;
import java.net.*;

public class Client {
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