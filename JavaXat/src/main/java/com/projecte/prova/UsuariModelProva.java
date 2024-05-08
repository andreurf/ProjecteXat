package com.projecte.prova;

import java.util.Date;

public class UsuariModelProva {

    private String nomUsuari;
    private String contrasenya;
    private Date horaConnexio;
    private String grup;

    public UsuariModelProva() {
    }

    public UsuariModelProva(String nomUsuari, String contrasenya, Date horaConnexio, String grup) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.horaConnexio = horaConnexio;
        this.grup = grup;
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

}
