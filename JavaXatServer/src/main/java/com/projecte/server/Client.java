package com.projecte.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket cs = new Socket("localhost", 7777);

            System.out.println("Establint la connexió");
            
            InputStream is = cs.getInputStream();
            OutputStream os = cs.getOutputStream();

            String missatge = "Usuari";
            os.write(missatge.getBytes());

            byte[] resposta = new byte[50];
            int nombreBytes = is.read(resposta);
            String missatgeOK = new String(resposta, 0, nombreBytes);
            
            System.out.println(missatgeOK.toString());

            cs.close();
            
            System.out.println("Usuari exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
