package com.projecte.swing;

import java.awt.Color;
import javax.swing.Icon;

/**
 *
 * @author andreu i quim
 */
public class ChatD extends javax.swing.JLayeredPane {

    public ChatD() {
        initComponents();
        txt.setBackground(new Color(179, 233, 255));
    }    
    
    public void setText(String text){
        if(text.equals("")){
            txt.amagarText();
        } else{
            txt.setText(text);
        }
        txt.seen();
    }
    
    public void setImatge(Icon... imatge){
        txt.setImatge(true, imatge);
    }
    
    public void setTime(){
        txt.setTemps("10:30 PM");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.projecte.swing.ChatItem();

        txt.setMinimumSize(new java.awt.Dimension(21,36));

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.swing.ChatItem txt;
    // End of variables declaration//GEN-END:variables
}
