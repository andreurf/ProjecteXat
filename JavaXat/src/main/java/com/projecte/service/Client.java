package com.projecte.service;

import com.projecte.models.Usuari;
import com.projecte.swing.Xat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private static Usuari usuari;
    
    public static void iniciarClient(Usuari user) {
        try {
            System.out.println("Creant Socket client");
            
            usuari = user;

            String ip = user.getIp();

            Socket cs = new Socket(ip, 7878);
            //Socket cs = new Socket();

            System.out.println("Establint la connexi�");

            //InetSocketAddress addr= new InetSocketAddress("localhost",7878);
            //cs.connect(addr);
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            String envUser = user.getUsuari();
            os.write(envUser.getBytes());

            byte[] messageBytes = new byte[50];
            is.read(messageBytes);
            String message = new String(messageBytes).trim();

            // Si el mensaje es "OPEN_CHAT", abre la ventana de chat
            if (message.equals("OPEN_CHAT")) {
                Xat xat = new Xat();
                xat.setVisible(true);
            }

            cs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNomUsuari() {
        return usuari.getUsuari();
    }
    
}
