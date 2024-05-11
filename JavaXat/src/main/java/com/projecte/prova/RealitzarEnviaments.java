package com.projecte.prova;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isard
 */
public class RealitzarEnviaments extends Thread {

    private ArrayList<Usuari> usuaris;
    private Usuari usuari;
    private Usuari usu;
    private OutputStream out;
    String nom;

    public RealitzarEnviaments(ArrayList<Usuari> usuaris, Usuari usuari) {
        this.usuaris = usuaris;
        this.usuari = usuari;
    }

    @Override
    public void run() {
        try {
            for (Usuari usu : usuaris) {

                this.usu = usu;
                nom = usu.getNomUsuari();
                // Obtindre el socket del usuari
                Socket socket = usu.getSocket();
                out = socket.getOutputStream();

                //Enviar missatge als usuaris que es conecten
                String missatge = nom + " s'ha unit.\n";
                out.write(missatge.getBytes());
                out.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
            usuaris.remove(usu);
            try {
                for (Usuari usu : usuaris) {

                    this.usu = usu;
                    nom = usu.getNomUsuari();
                    // Obtindre el socket del usuari
                    Socket socket = usu.getSocket();
                    out = socket.getOutputStream();

                    //Enviar missatge als usuaris que es conecten
                    String missatge = usuari.getNomUsuari() + " s'ha desconectat.";
                    out.write(missatge.getBytes());
                    out.flush();

                }
            } catch (IOException ec) {
                ec.printStackTrace();
            }
        }
    }

}
