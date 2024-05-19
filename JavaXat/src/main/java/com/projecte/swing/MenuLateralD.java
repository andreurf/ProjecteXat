package com.projecte.swing;

import com.projecte.prova.Client;
import com.toedter.calendar.JCalendar;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralD extends javax.swing.JPanel {
    
    private final ChatTitol chatTitol;
    private final ChatBody chatBody;
    private final Client client;
    
    public MenuLateralD(ChatTitol chatTitol, ChatBody chatBody, Client client) {
        initComponents();
        this.chatTitol = chatTitol;
        this.chatBody = chatBody;
        this.client = client;
        init();
    }
    
    private void init(){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new java.awt.BorderLayout());
        add(jCalendar1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar1;
    // End of variables declaration//GEN-END:variables
}
