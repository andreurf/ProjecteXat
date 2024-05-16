package com.projecte.prova;

import com.projecte.swing.ChatBody;
import com.projecte.swing.ChatTitol;
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

    public void iniciarReceptorMissatges(ChatBody chatBody, ChatTitol chatTitol) {
        new Thread(() -> {
            try {
                while (true) {
                    String nom = in.readLine();
                    String missatge = in.readLine();
                    System.out.println(nom + ": " + missatge);
                    if (nom != null && missatge != null) {
                        System.out.println("/////////////////////////" + nomUsuari + "\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
                        System.out.println(missatge);
                        if (missatge.equals(" s'ha unit al xat") || missatge.equals(" s'ha desconectat")) {
                            SwingUtilities.invokeLater(() -> chatBody.addEstat(nom + missatge));
                        } else {
                            if (missatge.startsWith("/p")) {
                                mostrarMissatge(nom, missatge, chatBody);
                            } else {
                                mostrarMissatge(nom, missatge, chatBody);
                            }
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

    public static void mostrarMissatge(String nom, String missatge, ChatBody chatBody) {
        System.out.println(nom + ": " + missatge);
        if (nom.equals(nomUsuari)) {
            SwingUtilities.invokeLater(() -> chatBody.addItemD(missatge));
        } else {
            SwingUtilities.invokeLater(() -> chatBody.addItemE(missatge, nom));
        }
    }

    public static String getNomUsuari() {
        return nomUsuari;
    }
}

/*package com.projecte.prova;
import com.projecte.swing.ChatBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.SwingUtilities;

public class Client {

    private final String serverIP;
    private final int serverPort;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nomUsuari;

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
        out.println(text);
    }

    public void iniciarReceptorMissatges(ChatBody chatBody) {
        final String nombreUsuario = nomUsuari; // Variable local final

        new Thread(() -> {
            try {
                while (true) {
                    String nom = in.readLine();
                    String missatge = in.readLine();
                    if (nom != null && missatge != null) {
                        if (missatge.startsWith("/pm")) {
                            // Este es un mensaje privado
                            final String miss = missatge.substring(4); // Eliminar el prefijo /pm
                            SwingUtilities.invokeLater(() -> chatBody.addItemD(miss));
                        } else {
                            if (missatge.equals(" s'ha unit al xat") || missatge.equals(" s'ha desconectat")) {
                                SwingUtilities.invokeLater(() -> chatBody.addEstat(nom + missatge));
                            } else {
                                if (nom.equals(nombreUsuario)) {
                                    SwingUtilities.invokeLater(() -> chatBody.addItemD(missatge));
                                } else {
                                    SwingUtilities.invokeLater(() -> chatBody.addItemE(missatge, nom));
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public String getNomUsuari() {
        return nomUsuari;
    }
}*/
