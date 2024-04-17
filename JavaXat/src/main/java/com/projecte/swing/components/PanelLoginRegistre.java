package com.projecte.swing.components;

import com.projecte.models.Usuari;
import com.projecte.service.MongoDBConexio;
import com.projecte.swing.Xat;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Andreu i Quim
 */
public class PanelLoginRegistre extends javax.swing.JLayeredPane {

    private MongoDBConexio dbConnection;

    public PanelLoginRegistre() {
        initComponents();
        dbConnection = new MongoDBConexio();
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
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTRAR-SE");
        cmd.addActionListener((ActionEvent e) -> {
            String usuario = txtUser.getText();
            String email = txtEmail.getText();
            String contrasena = txtPass.getText();
            Usuari newUser = new Usuari(usuario, contrasena, email);

            try {
                boolean usuariExisteix = dbConnection.validarUsuari(usuario);
                boolean emailExisteix = dbConnection.validarEmail(email);

                if (usuariExisteix) {
                    JOptionPane.showMessageDialog(null, "El nom d'usuari ja està en ús", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (emailExisteix) {
                    JOptionPane.showMessageDialog(null, "El correu electrònic ja està en ús", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dbConnection.insertUser(newUser);
                    JOptionPane.showMessageDialog(null, "Usuari registrat correctament");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en registrar l'usuari: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

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
        cmdRenovarPass.setForeground(new Color(100, 100, 100));
        cmdRenovarPass.setBorderPainted(false);
        cmdRenovarPass.setFont(new Font("sansserif", 1, 12));
        cmdRenovarPass.setContentAreaFilled(false);
        cmdRenovarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(cmdRenovarPass);

        Boto cmd = new Boto();
        cmd.setBackground(new Color(166, 35, 35));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Iniciar Sesión");
        cmd.addActionListener((ActionEvent e) -> {
            String usuari = txtUser.getText();
            String contrasenya = txtPass.getText();

            try {
                boolean loginOk = dbConnection.iniciarSecio(usuari, contrasenya);

                if (loginOk) {
                    Xat x = new Xat();
                    x.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en iniciar sessió: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        login.add(cmd, "w 40%, h 40");


    }

    public void mostrarRegistre(boolean mostrar) {
        if (mostrar) {
            registre.setVisible(true);
            login.setVisible(false);
        } else {
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
