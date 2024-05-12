package com.projecte.prova;

import com.projecte.swing.ChatBody;
import java.io.*;
import java.net.*;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

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
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    StringBuilder mensajeActual = new StringBuilder();
                    while (true) {
                        String linea = in.readLine();
                        if (linea == null) {
                            // Si la línea es nula, significa que la conexión se ha cerrado
                            break;
                        }
                        if (linea.startsWith("/pm")) {
                            // Este es un mensaje privado
                            String mensajePrivado = linea.substring(4); // Eliminar el prefijo /pm
                            chatBody.addItemD(mensajePrivado);
                        } else {
                            // Es parte de un mensaje normal
                            mensajeActual.append(linea).append("\n");

                            // Verificar si es el final del mensaje
                            if (linea.isEmpty()) {
                                // Este es el final del mensaje, agregarlo a la interfaz de usuario
                                String mensajeCompleto = mensajeActual.toString().trim();
                                mensajeActual.setLength(0); // Limpiar el StringBuilder para el próximo mensaje
                                String[] partes = mensajeCompleto.split(": ", 2);
                                String nom = partes[0];
                                String mensaje = partes[1];
                                System.out.println(nom + ": " + mensaje);
                                if (nom.equals(getNomUsuari())) {
                                    chatBody.addItemD(mensaje);
                                } else {
                                    chatBody.addItemE(mensaje, nom);
                                }
                                chatBody.revalidate();
                                chatBody.repaint();
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        worker.execute();
    }

    public static String getNomUsuari() {
        return nomUsuari;
    }
}
