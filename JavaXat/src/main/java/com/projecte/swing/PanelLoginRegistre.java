package com.projecte.swing;

import com.projecte.models.Usuari;
import com.projecte.service.Client;
import com.projecte.service.ServidorMDB;
import com.projecte.swing.components.Boto;
import com.projecte.swing.components.JTextFieldPassword;
import com.projecte.swing.components.JTextFieldPersonalitzat;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    private ServidorMDB dbConnection;

    public PanelLoginRegistre() {
        initComponents();
        dbConnection = new ServidorMDB();
        initRegistre();
        initLogin();
        login.setVisible(false);
        registre.setVisible(true);

    }

    private void initRegistre() {
        registre.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("REGISTRA'T");
        label.setFont(new Font("sanserif", 1, 30));
        label.setForeground(new Color(166, 35, 35));

        // TextField Usuari
        JTextFieldPersonalitzat txtUser = new JTextFieldPersonalitzat();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUser.setHint("Usuari");

        //TextField Contrassenya
        JTextFieldPassword txtPass = new JTextFieldPassword();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPass.setHint("Contrassenya");

        Boto cmd = new Boto();
        cmd.setBackground(new Color(166, 35, 35));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("REGISTRAR-SE");
        cmd.addActionListener((ActionEvent e) -> {
            String usuario = txtUser.getText();
            String contrasena = txtPass.getText();

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Els camps d'usuari, correu electrònic i contrasenya no poden estar buits", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Usuari newUser = new Usuari(usuario, contrasena);
            newUser.setUsuari(usuario);

            try {
                boolean usuariExisteix = dbConnection.validarUsuari(usuario);

                if (usuariExisteix) {
                    JOptionPane.showMessageDialog(null, "El nom d'usuari ja està en ús", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    dbConnection.insertUser(newUser);
                    JOptionPane.showMessageDialog(null, "Usuari registrat correctament");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en registrar l'usuari: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        txtUser.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrar(txtUser, txtPass);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        txtPass.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrar(txtUser, txtPass);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });

        registre.add(label);
        registre.add(txtUser, "w 60%");
        registre.add(txtPass, "w 60%");
        registre.add(cmd, "w 40%, h 40");

    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("INICIAR SESSIÓ");
        label.setFont(new Font("sanserif", 1, 30));
        label.setForeground(new Color(166, 35, 35));

        // TextField Usuari
        JTextFieldPersonalitzat txtUser = new JTextFieldPersonalitzat();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUser.setHint("Usuari");

        //TextField Contrassenya
        JTextFieldPassword txtPass = new JTextFieldPassword();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPass.setHint("Contrassenya");

        JButton cmdRenovarPass = new JButton("Has olvidat la contrassenya?");
        cmdRenovarPass.setForeground(new Color(100, 100, 100));
        cmdRenovarPass.setBorderPainted(false);
        cmdRenovarPass.setFont(new Font("sansserif", 1, 12));
        cmdRenovarPass.setContentAreaFilled(false);
        cmdRenovarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //TextField IP Servidor
        JTextFieldPersonalitzat txtIPServidor = new JTextFieldPersonalitzat();
        txtIPServidor.setPrefixIcon(new ImageIcon(getClass().getResource("/location.png")));
        txtIPServidor.setHint("IP Servidor");

        // Afegir accio per a poder fer enter
        txtUser.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inciarSessio(txtUser, txtPass, txtIPServidor);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        txtPass.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inciarSessio(txtUser, txtPass, txtIPServidor);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        txtIPServidor.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    inciarSessio(txtUser, txtPass, txtIPServidor);
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });
        login.add(label);
        login.add(txtUser, "w 60%");
        login.add(txtPass, "w 60%");
        login.add(cmdRenovarPass);
        login.add(txtIPServidor, "w 60%");

        Boto cmd = new Boto();
        cmd.setBackground(new Color(166, 35, 35));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("Iniciar Sesión");
        cmd.addActionListener((ActionEvent e) -> {
            inciarSessio(txtUser, txtPass, txtIPServidor);
        });

        login.add(cmd, "w 40%, h 40");

    }

    public void inciarSessio(JTextFieldPersonalitzat txtUser, JTextFieldPassword txtPass, JTextFieldPersonalitzat txtIPServidor) {
        String usuari = txtUser.getText();
        String contrasenya = txtPass.getText();
        String ipServidor = txtIPServidor.getText();

        try {

            boolean loginOk = dbConnection.iniciarSecio(usuari, contrasenya);

            if (loginOk) {
                Usuari user = new Usuari(usuari, contrasenya, ipServidor);
                Client.iniciarClient(user);
                SwingUtilities.getWindowAncestor(this).setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en iniciar sessió: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void registrar(JTextFieldPersonalitzat txtUser, JTextFieldPassword txtPass) {
        String usuari = txtUser.getText();
        String contrasena = txtPass.getText();

        if (usuari.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Els camps d'usuari, correu electrònic i contrasenya no poden estar buits", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuari newUser = new Usuari(usuari, contrasena);
        newUser.setUsuari(usuari);

        try {
            boolean usuariExisteix = dbConnection.validarUsuari(usuari);

            if (usuariExisteix) {
                JOptionPane.showMessageDialog(null, "El nom d'usuari ja està en ús", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                dbConnection.insertUser(newUser);
                JOptionPane.showMessageDialog(null, "Usuari registrat correctament");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en registrar l'usuari: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
