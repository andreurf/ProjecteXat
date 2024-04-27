package com.projecte.swing;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author andreu i quim
 */
public class ChatItem extends javax.swing.JLayeredPane {
    
    private JLabel label;
    
    public ChatItem() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
    }
    
    public void setPerfilUsuari(String usuari){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));
        JButton cmd = new JButton(usuari);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30, 121, 213));
        cmd.setFont(new java.awt.Font("sansserif", 1, 13));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        layer.add(cmd);
        add(layer, 0);
    }

    public void setText(String text) {
        txt.setText(text);
    }
    
    public void setTemps(String time){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        layer.setBorder(new EmptyBorder(0, 5, 10, 5));
        label = new JLabel(time);
        label.setForeground(new Color(110, 110, 110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }
    
    public void setImatge(boolean posicio, Icon... imatge){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(posicio?FlowLayout.RIGHT:FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        ChatImatge chatImatge = new ChatImatge(posicio);
        chatImatge.addImatge(imatge);
        layer.add(chatImatge);
        add(layer);
    }
    
    public void sendSucces(){
        if(label!=null){
            label.setIcon(new ImageIcon(getClass().getResource("/tick.png")));
        }
    }
    
    public void seen() {
        if (label != null) {
            label.setIcon(new ImageIcon(getClass().getResource("/double_tick.png")));
        }
    }
    
    public void amagarText(){
        txt.setVisible(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.raven.swing.JIMSendTextPane();

        setMinimumSize(new java.awt.Dimension(62, 20));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 5, 10));
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
