package com.projecte.models;

/**
 *
 * @author Usuario
 */
public class Missatge {
    
    private String text;
    private String temps;
    private String nomUsuari;
    
    public Missatge(){
        
    }
    
    public Missatge(String nomUsuari, String text){
        this.nomUsuari = nomUsuari;
        this.text = text;
    }
    
    public Missatge(String nomUsuari, String text, String temps){
        this.nomUsuari = nomUsuari;
        this.text = text;
        this.temps = temps;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }
    
}
