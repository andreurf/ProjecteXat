package com.projecte.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class ChatItem extends javax.swing.JLayeredPane {

    public ChatItem() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0,0,0));
        txt.setOpaque(false);
    }

    public void setText(String text) {
        txt.setText(text);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.raven.swing.JIMSendTextPane();

        setMinimumSize(new java.awt.Dimension(21, 36));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txt.setSelectedTextColor(new java.awt.Color(94, 190, 255));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
