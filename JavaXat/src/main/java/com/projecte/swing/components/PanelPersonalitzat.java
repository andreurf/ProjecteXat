package com.projecte.swing.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Andreu i Quim
 */
public class PanelPersonalitzat extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###");
    private ActionListener event;
    private MigLayout layout;
    private JLabel titol;
    private JLabel desc1;
    private JLabel desc2;
    private BotoDisseny boto;
    private boolean isLogin;

    public PanelPersonalitzat() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();
    }

    private void init() {
        titol = new JLabel("Benvigut de nou!");
        titol.setFont(new Font("sanserif",1,30));
        titol.setForeground(new Color(245,245,245));
        add(titol);
        desc1 = new JLabel("Seguir connectat amb nosaltre siusplau");
        desc1.setForeground(new Color(245,245,245));
        add(desc1);
        desc2 = new JLabel("Inicia Sessió amb les teves credencials");
        desc2.setForeground(new Color(245,245,245));
        add(desc2);
        boto = new BotoDisseny();
        boto.setBackground(new Color(255,255,255));
        boto.setForeground(new Color(255,255,255));
        boto.setText("INICIA SESSIÓ");
        boto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.actionPerformed(e);
            }
        });
        boto.setBorderPainted(false);
        add(boto,"w 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(166, 35, 35), 0, getHeight(), new Color(116, 22, 22));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void registreEsquerra(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(false);
        layout.setComponentConstraints(titol, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(desc1, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(desc2, "pad 0 -" + v + "% 0 0");
    }

    public void registreDreta(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(false);
        layout.setComponentConstraints(titol, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(desc1, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(desc2, "pad 0 -" + v + "% 0 0");
    }

    public void loginEsquerra(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(true);
        layout.setComponentConstraints(titol, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(desc1, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(desc2, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginDreta(double v) {
        v = Double.valueOf(df.format(v).replace(",", "."));
        login(true);
        layout.setComponentConstraints(titol, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(desc1, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(desc2, "pad 0 " + v + "% 0 " + v + "%");
    }

    private void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                titol.setText("hello, Friend!");
                desc1.setText("Enter your personal details");
                desc2.setText("and start journey with us");
                boto.setText("SIGN UP");
            } else {
                titol.setText("Welcome Back!");
                desc1.setText("To keep connected with us please");
                desc2.setText("login with your personal info");
                boto.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
