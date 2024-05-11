package com.projecte.prova;

import com.projecte.swing.ChatBody;
import java.io.*;
import java.net.*;
import javax.swing.SwingUtilities;

public class Client {

    private final String serverIP;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private static PrintWriter out;
    private static String nomUsuari;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public boolean obtindreUsuari(String nomUsuari) throws IOException {
        socket = new Socket(serverIP, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        this.nomUsuari = nomUsuari;

        // Enviar credenciales al servidor
        out.println(nomUsuari);

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
    
    public void iniciarReceptorMissatges(ChatBody chatBody) {
        new Thread(() -> {
            try {
                while (true) {
                    String nom = in.readLine();
                    String missatge = in.readLine();
                    if (nom != null && missatge != null) {
                        System.out.println("/////////////////////////"+nomUsuari+"\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
                        System.out.println(nom + ": " + missatge);
                        if (nom.equals(nomUsuari)) {
                            SwingUtilities.invokeLater(() -> chatBody.addItemD(missatge));
                        } else {
                            SwingUtilities.invokeLater(() -> chatBody.addItemE(missatge, nom));
                        }
                        chatBody.revalidate();
                        chatBody.repaint();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String getNomUsuari() {
        return nomUsuari;
    }
}
