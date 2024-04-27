package com.projecte.swing;

import java.awt.Color;
import javax.swing.Icon;

/**
 *
 * @author andreu i quim
 */
public class ChatEPerfil extends javax.swing.JLayeredPane {

    public ChatEPerfil() {
        initComponents();
        txt.setBackground(new Color(242,242,242));
    }
    
    public void setPerfilUsuari(String usuari){
        txt.setPerfilUsuari(usuari);
    }
    
    public void setImatgePerfil(Icon imatge) {
        imatgePerfil.setImage(imatge);
    }
    
    public void setText(String text){
        if(text.equals("")){
            txt.amagarText();
        } else{
            txt.setText(text);
        }
    }
    
    public void setImatge(Icon... imatge){
        txt.setImatge(false, imatge);
    }
    
    public void setTime(){
        txt.setTemps("10:30 PM");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        imatgePerfil = new com.projecte.swing.components.ImatgeAvatar();
        txt = new com.projecte.swing.ChatItem();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        imatgePerfil.setImage(new javax.swing.ImageIcon(getClass().getResource("/hombre.png")));
        imatgePerfil.setBorderSize(0);
        imatgePerfil.setMaximumSize(new java.awt.Dimension(31, 31));
        imatgePerfil.setMinimumSize(new java.awt.Dimension(31, 31));
        imatgePerfil.setPreferredSize(new java.awt.Dimension(31, 31));

        jLayeredPane1.setLayer(imatgePerfil, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(imatgePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(imatgePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jLayeredPane1);

        txt.setMinimumSize(new java.awt.Dimension(21,36));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.swing.components.ImatgeAvatar imatgePerfil;
    private javax.swing.JLayeredPane jLayeredPane1;
    private com.projecte.swing.ChatItem txt;
    // End of variables declaration//GEN-END:variables
}
