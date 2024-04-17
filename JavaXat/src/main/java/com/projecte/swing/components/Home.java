package com.projecte.swing.components;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class Home extends javax.swing.JLayeredPane {
    
    public Home() {
        initComponents();
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx, filly", "0[200!]12[fill, 100%]5[200!]0", "0[fill]0"));
        this.add(new MenuLateralE());
        this.add(new ChatText());
        this.add(new MenuLateralD());

        
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
