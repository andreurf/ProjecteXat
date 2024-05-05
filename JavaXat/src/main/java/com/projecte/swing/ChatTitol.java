package com.projecte.swing;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author andreu i quim
 */
public class ChatTitol extends javax.swing.JPanel {

    public ChatTitol() {
        initComponents();
    }

    public void setNomUsuari(String nomUsuari) {
        lbNom.setText(nomUsuari);
        lbNom.revalidate();
        lbNom.repaint();
    }

    public void estatActiu() {
        lbStatus.setText("Actiu");
        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
    }

    public void setStatusText(String text) {
        lbStatus.setText(text);
        lbStatus.setForeground(new Color(160, 160, 160));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbNom = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbNom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNom.setForeground(new java.awt.Color(63, 63, 63));
        lbNom.setText("Nom");
        layer.add(lbNom);

        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
        lbStatus.setText("Actiu");
        layer.add(lbStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(430, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbNom;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
