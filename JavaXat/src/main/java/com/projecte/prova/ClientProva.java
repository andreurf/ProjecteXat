package com.projecte.prova;

import java.io.*;
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
