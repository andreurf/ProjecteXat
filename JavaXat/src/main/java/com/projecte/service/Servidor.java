package com.projecte.service;

import com.projecte.models.Usuari;
import com.projecte.swing.Xat;
import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    private static ArrayList<Usuari> usuaris = new ArrayList<Usuari>();

    public static void main(String[] args) {
        try {
            System.out.println("Creant Socket servidor");

            ServerSocket serverSocket = new ServerSocket();

            InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 7878);

            serverSocket.bind(addr);
            while (true) {

                Socket newSocket = serverSocket.accept();

//                Xat x = new Xat();
//                x.setVisible(true);
                InputStream is = newSocket.getInputStream();
                OutputStream os = newSocket.getOutputStream();

                byte[] missatge = new byte[50];

                is.read(missatge);

                os.write("OPEN_CHAT".getBytes());

                Usuari user = new Usuari(new String(missatge));
                usuaris.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getUsuariConectat() {
        ArrayList<String> username = new ArrayList<>();
        for (int i = 0; i < usuaris.size(); i++) {
            username.add(usuaris.get(i).getUsuari());
        }
        return username;
    }
}
