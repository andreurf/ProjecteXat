package com.projecte.prova;

import java.util.Date;

public class Missatge implements Comparable<Missatge> {

    private String nomUsuari;
    private String missatge;
    private Date dataHora;
    private String grup;

    public Missatge() {
    }

    public Missatge(String nomUsuari, String missatge, Date dataHora, String grup) {
        this.nomUsuari = nomUsuari;
        this.missatge = missatge;
        this.dataHora = dataHora;
        this.grup = grup;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    @Override
    public String toString() {
        return "MissatgeModelProva{" +
                "nomUsuari='" + nomUsuari + '\'' +
                ", missatge='" + missatge + '\'' +
                ", dataHora=" + dataHora +
                ", grup='" + grup + '\'' +
                '}';
    }
    
    @Override
    public int compareTo(Missatge otroMensaje) {
        return this.getDataHora().compareTo(otroMensaje.getDataHora());
    }
}
