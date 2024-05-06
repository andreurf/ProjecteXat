package com.projecte.prova;

import java.util.Date;

public class MissatgeModelProva {

    private String nomUsuari;
    private String missatge;
    private Date dataHora;
    private String grup;

    public MissatgeModelProva() {
    }
    

    public MissatgeModelProva(String nomUsuari, String missatge, Date dataHora, String grup) {
        this.nomUsuari = nomUsuari;
        this.missatge = missatge;
        this.dataHora = dataHora;
        this.grup = grup;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public String getMissatge() {
        return missatge;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public String getGrup() {
        return grup;
    }

}
