package com.projecte.swing;

import com.projecte.prova.Client;
import com.projecte.swing.ChatText;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class Home extends javax.swing.JLayeredPane {
    
    private Client client;
    
    public Home(Client client) {
        this.client = client;
        initComponents();
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        ChatTitol chatTitol = new ChatTitol();
        ChatBody chatBody = new ChatBody();
        this.add(new MenuLateralE(chatTitol, chatBody, client),"cell 0 0, grow");
        this.add(new ChatText(chatTitol, chatBody, client), "cell 1 0, grow");
        this.add(new MenuLateralD(chatTitol, chatBody, client), "cell 2 0, grow, wmin 200px, hmin 400px");

        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
