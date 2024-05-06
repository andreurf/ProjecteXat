package com.projecte.service;

import com.projecte.models.Missatge;
import com.projecte.models.Usuari;
import com.projecte.swing.Xat;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    private static Usuari usuari;
    private static Socket cs;

    public static void iniciarClient(Usuari user) {
        try {
            System.out.println("Creant Socket client");

            usuari = user;

            String ip = user.getIp();

            cs = new Socket(ip, 7878);

            System.out.println("Establint la connexi�");

            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            String envUser = user.getUsuari();
            os.write(envUser.getBytes());

            byte[] messageBytes = new byte[50];
            is.read(messageBytes);
            String message = new String(messageBytes).trim();

            if (message.equals("OPEN_CHAT")) {
                Xat xat = new Xat();
                xat.setVisible(true);
//                byte[] missBytes = new byte[50];
//                while (true) {
//                    is.read(missBytes);
//                    String miss = new String(missBytes).trim();
//                    Xat.actualitzarXat(miss);
//                }
            }

//            cs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getNomUsuari() {
        return usuari.getUsuari();
    }

    private static void enviarMensaje(Missatge mensaje) {
        try {
            OutputStream os = cs.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(mensaje);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
