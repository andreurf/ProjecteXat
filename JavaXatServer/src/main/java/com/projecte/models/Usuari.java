
package com.projecte.models;

/**
 *
 * @author Andreu i Quim
 */
public class Usuari {
    
    private String socket;
    private String usuari;
    private String password;
    private String email;

    public Usuari() {
        
    }

    public Usuari(String socket, String usuari, String password, String email) {
        this.socket = socket;
        this.usuari = usuari;
        this.password = password;
        this.email = email;
    }
    
    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
