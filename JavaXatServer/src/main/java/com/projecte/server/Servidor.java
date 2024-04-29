package com.projecte.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author andreu i quim
 */
public class Servidor extends Thread {

    public static void main(String[] args) throws InterruptedException {
        try {

            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress addr = new InetSocketAddress("localhost", 7777);
            serverSocket.bind(addr);

            while (true) {
                System.out.println("Servidor ja endollat");

                Socket newSocket = serverSocket.accept();

                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();

                byte[] missatge = new byte[50];
                int nombreBytes = is.read(missatge);
                String missatgeOK = new String(missatge, 0, nombreBytes);
                
                System.out.println("Hola " + missatgeOK);

                String resposta2 = "Missatge";
                os.write(resposta2.getBytes());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
