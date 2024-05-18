package com.projecte.prova;

import java.net.Socket;
import java.util.Date;

public class Usuari {

    private String nomUsuari;
    private String contrasenya;
    private Date horaConnexio;
    private String grup;
    private Socket socket;
    private boolean estat;
    private String receptor;

    public Usuari() {
    }

    public Usuari(String nomUsuari, String contrasenya, Date horaConnexio, String grup) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.horaConnexio = horaConnexio;
        this.grup = grup;
    }
    
    public Usuari(String nomUsuari, String contrasenya, Date horaConnexio, String grup, Socket socket) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.horaConnexio = horaConnexio;
        this.grup = grup;
    }

    public Usuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }
    
    public Usuari(String nomUsuari, Socket socket) {
        this.nomUsuari = nomUsuari;
        this.socket = socket;
    }

    public Usuari(String nomUsuari, String contrasenya) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
    }
    
    public Usuari(String nomUsuari, Socket socket, boolean estat) {
        this.nomUsuari = nomUsuari;
        this.socket = socket;
        this.estat = estat;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Date getHoraConnexio() {
        return horaConnexio;
    }

    public void setHoraConnexio(Date horaConnexio) {
        this.horaConnexio = horaConnexio;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public String getReceptor(){
        return receptor;
    }
    
    public void setReceptor(String receptor){
        this.receptor = receptor;
    }

}
