package com.projecte.swing.components;

import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Andreu i Quim
 */
public class PanelLoginRegistre extends javax.swing.JLayeredPane {

    public PanelLoginRegistre() {
        initComponents();
        initRegistre();
        initLogin();
        //login.setVisible(false);
        //registre.setVisible(true);

    }

    private void initRegistre() {
        registre.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("REGISTRA'T");
        label.setFont(new Font("sanserif", 1, 30));
        label.setForeground(new Color(166, 35, 35));
        registre.add(label);
        
        // TextField Usuari
        JTextFieldPersonalitzat txtUser = new JTextFieldPersonalitzat();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUser.setHint("Usuari");
        registre.add(txtUser, "w 60%");
        
        //TextField Email
        JTextFieldPersonalitzat txtEmail = new JTextFieldPersonalitzat();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/mail.png")));
        txtEmail.setHint("Correu Electrònic");
        registre.add(txtEmail, "w 60%");
        
        //TextField Contrassenya
        JTextFieldPersonalitzat txtPass = new JTextFieldPersonalitzat();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPass.setHint("Contrassenya");
        registre.add(txtPass, "w 60%");
        
        Boto cmd = new Boto();
        cmd.setBackground(new Color(166, 35, 35));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("REGISTRAR-SE");
        registre.add(cmd, "w 40%, h 40");
        
    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("INICIAR SESSIÓ");
        label.setFont(new Font("sanserif", 1, 30));
        label.setForeground(new Color(166, 35, 35));
        login.add(label);
        
        // TextField Usuari
        JTextFieldPersonalitzat txtUser = new JTextFieldPersonalitzat();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUser.setHint("Usuari");
        login.add(txtUser, "w 60%");
        
        //TextField Contrassenya
        JTextFieldPersonalitzat txtPass = new JTextFieldPersonalitzat();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPass.setHint("Contrassenya");
        login.add(txtPass, "w 60%");
        JButton cmdRenovarPass = new JButton("Has olvidat la contrassenya?");
        cmdRenovarPass.setForeground(new Color(100,100,100));
        cmdRenovarPass.setBorderPainted(false);
        cmdRenovarPass.setFont(new Font("sansserif",1,12));
        cmdRenovarPass.setContentAreaFilled(false);
        cmdRenovarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdRenovarPass);
        
        Boto cmd = new Boto();
        cmd.setBackground(new Color(166, 35, 35));
        cmd.setForeground(new Color(250,250,250));
        cmd.setText("Inciar Sessió");
        login.add(cmd, "w 40%, h 40");
        
    }
    
    public void mostrarRegistre(boolean mostrar){
        if(mostrar){
            registre.setVisible(true);
            login.setVisible(false);
        }else{
            registre.setVisible(false);
            login.setVisible(true);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        registre = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        registre.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registreLayout = new javax.swing.GroupLayout(registre);
        registre.setLayout(registreLayout);
        registreLayout.setHorizontalGroup(
            registreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registreLayout.setVerticalGroup(
            registreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(registre, "card2");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel registre;
    // End of variables declaration//GEN-END:variables
}
