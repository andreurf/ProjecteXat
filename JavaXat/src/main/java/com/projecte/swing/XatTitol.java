package com.projecte.swing;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author andreu i quim
 */
public class XatTitol extends javax.swing.JPanel {

    private boolean isDarkTheme = false;

    public XatTitol() {
        initComponents();
    }

    public void setNomUsuari(String nomUsuari) {
        lbNom.setText(nomUsuari);
        lbNom.revalidate();
        lbNom.repaint();
    }

    public void estatActiu() {
        lbEstat.setText("Actiu");
        lbEstat.setForeground(isDarkTheme ? new java.awt.Color(75, 201, 100) : new java.awt.Color(40, 147, 59));
    }

    public void setEstatText() {
        lbEstat.setText("Inactiu");
        lbEstat.setForeground(new Color(160, 160, 160));
    }

    public String getLbNom() {
        return lbNom.getText();
    }

    public void changeTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        Color bgColor = isDarkTheme ? new Color(55, 55, 55) : new Color(242, 242, 242);
        Color textColor = isDarkTheme ? new Color(200, 200, 200) : new Color(63, 63, 63);
        Color activeColor = isDarkTheme ? new Color(75, 201, 100) : new Color(40, 147, 59);
        Color inactiveColor = isDarkTheme ? new Color(120, 120, 120) : new Color(160, 160, 160);

        this.setBackground(bgColor);
        lbNom.setForeground(textColor);
        lbEstat.setForeground(lbEstat.getText().equals("Actiu") ? activeColor : inactiveColor);

        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbNom = new javax.swing.JLabel();
        lbEstat = new javax.swing.JLabel();

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbNom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbNom.setForeground(new java.awt.Color(63, 63, 63));
        lbNom.setText("Nom");
        layer.add(lbNom);

        lbEstat.setForeground(new java.awt.Color(40, 147, 59));
        lbEstat.setText("Actiu");
        layer.add(lbEstat);

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
    private javax.swing.JLabel lbEstat;
    private javax.swing.JLabel lbNom;
    // End of variables declaration//GEN-END:variables
}
