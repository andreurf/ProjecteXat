
package com.projecte.models;

/**
 *
 * @author Andreu i Quim
 */
public class Usuari {
    
    private String usuari;
    private String password;

    public Usuari() {
        
    }

    public Usuari(String usuari, String password) {
        this.usuari = usuari;
        this.password = password;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
