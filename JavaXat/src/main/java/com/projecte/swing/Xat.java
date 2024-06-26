package com.projecte.swing;

import com.projecte.prova.Client;
import com.projecte.swing.components.ComponentAjustar;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author andreu i quim
 */
public class Xat extends javax.swing.JFrame {
    
    private Client client;
    
    public Xat(Client client) {
        this.client = client;
        initComponents();
        init();
    }

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/logo.png")).getImage());
        ComponentAjustar com = new ComponentAjustar();
        com.registerComponent(this);
        com.setMinimumSize(new Dimension(900, 500));
        com.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        com.setSnapSize(new Dimension(10, 10));
    }
    
    public void actualitzarXat(String missatge){
        
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        border = new javax.swing.JPanel();
        background = new javax.swing.JPanel();
        titol = new javax.swing.JPanel();
        cmdMinimitzar = new javax.swing.JButton();
        cmdTancar = new javax.swing.JButton();
        body = new javax.swing.JLayeredPane();
        home1 = new com.projecte.swing.Home(client);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        border.setBackground(new java.awt.Color(204, 204, 204));

        background.setBackground(new java.awt.Color(255, 255, 255));

        titol.setBackground(new java.awt.Color(204, 204, 204));
        titol.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titolMouseDragged(evt);
            }
        });
        titol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                titolMousePressed(evt);
            }
        });

        cmdMinimitzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/minimitzar.png")));
        cmdMinimitzar.setBorder(null);
        cmdMinimitzar.setBorderPainted(false);
        cmdMinimitzar.setContentAreaFilled(false);
        cmdMinimitzar.setFocusPainted(false);
        cmdMinimitzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdMinimitzarActionPerformed(evt);
            }
        });

        cmdTancar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tancar.png")));
        cmdTancar.setBorder(null);
        cmdTancar.setContentAreaFilled(false);
        cmdTancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTancarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titolLayout = new javax.swing.GroupLayout(titol);
        titol.setLayout(titolLayout);
        titolLayout.setHorizontalGroup(
            titolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, titolLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdMinimitzar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(cmdTancar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        titolLayout.setVerticalGroup(
            titolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmdMinimitzar, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            .addComponent(cmdTancar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        body.setLayout(new java.awt.CardLayout());
        body.add(home1, "card2");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(titol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        javax.swing.GroupLayout borderLayout = new javax.swing.GroupLayout(border);
        border.setLayout(borderLayout);
        borderLayout.setHorizontalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        borderLayout.setVerticalGroup(
            borderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borderLayout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(border, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int pX;
    private int pY;

    private void titolMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titolMouseDragged
        this.setLocation(getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_titolMouseDragged

    private void titolMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titolMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_titolMousePressed

    private void cmdMinimitzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdMinimitzarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdMinimitzarActionPerformed

    private void cmdTancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTancarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_cmdTancarActionPerformed

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Xat().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLayeredPane body;
    private javax.swing.JPanel border;
    private javax.swing.JButton cmdMinimitzar;
    private javax.swing.JButton cmdTancar;
    private com.projecte.swing.Home home1;
    private javax.swing.JPanel titol;
    // End of variables declaration//GEN-END:variables
}
