package com.projecte.swing;

import com.projecte.serveis.Client;
import com.projecte.serveis.MongoServeis;
import com.projecte.serveis.Usuari;
import com.projecte.components.Boto;
import com.projecte.components.JTextFieldPassword;
import com.projecte.components.JTextFieldPersonalitzat;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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

    private final MongoServeis mongoServeis;
    private final Color initialBgColor = Color.WHITE;
    private boolean isDarkTheme = false;

    private JLabel labelRegistre;
    private Boto cmdRegistre;
    private JLabel labelLogin;
    private Boto cmdLogin;

    private JTextFieldPersonalitzat txtUserRegistre;
    private JTextFieldPassword txtPassRegistre;
    private JTextFieldPersonalitzat txtUserLogin;
    private JTextFieldPassword txtPassLogin;
    private JTextFieldPersonalitzat txtIPServidor;

    public PanelLoginRegistre() {
        initComponents();
        mongoServeis = MongoServeis.getInstance();
        initRegistre();
        initLogin();
        login.setVisible(false);
        registre.setVisible(true);

    }

    public void changeTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        Color bgColor = isDarkTheme ? Color.DARK_GRAY : initialBgColor;

        registre.setBackground(bgColor);
        login.setBackground(bgColor);

        // Actualitzar colors dels botons
        if (labelRegistre != null && cmdRegistre != null) {
            labelRegistre.setForeground(isDarkTheme ? Color.WHITE : new Color(166, 35, 35));
            cmdRegistre.setBackground(isDarkTheme ? Color.LIGHT_GRAY : new Color(166, 35, 35));
            cmdRegistre.setForeground(Color.WHITE);
        }

        if (labelLogin != null && cmdLogin != null) {
            labelLogin.setForeground(isDarkTheme ? Color.WHITE : new Color(166, 35, 35));
            cmdLogin.setBackground(isDarkTheme ? Color.LIGHT_GRAY : new Color(166, 35, 35));
            cmdLogin.setForeground(Color.WHITE);
        }
    }

    public void esborrarDades() {
        if (txtUserRegistre != null) {
            txtUserRegistre.setText(""); // Esborrar el contingut del camp de text de registre
        }
        if (txtPassRegistre != null) {
            txtPassRegistre.setText(""); // Esborrar el contingut del camp de text de registre
        }
        if (txtUserLogin != null) {
            txtUserLogin.setText(""); // Esborrar el contingut del camp de text de login
        }
        if (txtPassLogin != null) {
            txtPassLogin.setText(""); // Esborrar el contingut del camp de text de login
        }
        if (txtIPServidor != null) {
            txtIPServidor.setText(""); // Esborrar el contingut del camp de text de login
        }
    }

    private void initRegistre() {
        registre.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        labelRegistre = new JLabel("REGISTRA'T");
        labelRegistre.setFont(new Font("sanserif", 1, 30));
        labelRegistre.setForeground(isDarkTheme ? Color.WHITE : new Color(166, 35, 35));

        // TextField Usuari
        txtUserRegistre = new JTextFieldPersonalitzat();
        txtUserRegistre.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUserRegistre.setHint("Usuari");

        //TextField Contrassenya
        txtPassRegistre = new JTextFieldPassword();
        txtPassRegistre.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPassRegistre.setHint("Contrassenya");

        cmdRegistre = new Boto();
        cmdRegistre.setBackground(isDarkTheme ? Color.LIGHT_GRAY : new Color(166, 35, 35));
        cmdRegistre.setForeground(Color.WHITE);
        cmdRegistre.setText("REGISTRAR-SE");
        cmdRegistre.addActionListener((ActionEvent e) -> {
            registrar(txtUserRegistre, txtPassRegistre);
        });

        txtUserRegistre.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrar(txtUserRegistre, txtPassRegistre);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        txtPassRegistre.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    registrar(txtUserRegistre, txtPassRegistre);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        registre.add(labelRegistre);
        registre.add(txtUserRegistre, "w 60%");
        registre.add(txtPassRegistre, "w 60%");
        registre.add(cmdRegistre, "w 40%, h 40");

    }

    private void initLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        labelLogin = new JLabel("INICIAR SESSIÓ");
        labelLogin.setFont(new Font("sanserif", 1, 30));
        labelLogin.setForeground(isDarkTheme ? Color.WHITE : new Color(166, 35, 35));

        // TextField Usuari
        txtUserLogin = new JTextFieldPersonalitzat();
        txtUserLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/user.png")));
        txtUserLogin.setHint("Usuari");

        //TextField Contrassenya
        txtPassLogin = new JTextFieldPassword();
        txtPassLogin.setPrefixIcon(new ImageIcon(getClass().getResource("/pass.png")));
        txtPassLogin.setHint("Contrassenya");

        JButton cmdRenovarPass = new JButton("Has olvidat la contrassenya?");
        cmdRenovarPass.setForeground(new Color(100, 100, 100));
        cmdRenovarPass.setBorderPainted(false);
        cmdRenovarPass.setFont(new Font("sansserif", 1, 12));
        cmdRenovarPass.setContentAreaFilled(false);
        cmdRenovarPass.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //TextField IP Servidor
        txtIPServidor = new JTextFieldPersonalitzat();
        txtIPServidor.setPrefixIcon(new ImageIcon(getClass().getResource("/location.png")));
        txtIPServidor.setHint("IP Servidor");

        // Afegir accio per a poder fer enter
        txtUserLogin.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        inciarSessio(txtUserLogin, txtPassLogin, txtIPServidor);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        txtPassLogin.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        inciarSessio(txtUserLogin, txtPassLogin, txtIPServidor);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        txtIPServidor.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        inciarSessio(txtUserLogin, txtPassLogin, txtIPServidor);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        login.add(labelLogin);
        login.add(txtUserLogin, "w 60%");
        login.add(txtPassLogin, "w 60%");
        login.add(cmdRenovarPass);
        login.add(txtIPServidor, "w 60%");

        cmdLogin = new Boto();
        cmdLogin.setBackground(isDarkTheme ? Color.LIGHT_GRAY : new Color(166, 35, 35));
        cmdLogin.setForeground(Color.WHITE);
        cmdLogin.setText("INICIA SESSIÓ");
        cmdLogin.addActionListener((ActionEvent e) -> {
            try {
                inciarSessio(txtUserLogin, txtPassLogin, txtIPServidor);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        login.add(cmdLogin, "w 40%, h 40");

    }

    public void inciarSessio(JTextFieldPersonalitzat txtUser, JTextFieldPassword txtPass, JTextFieldPersonalitzat txtIPServidor) throws IOException {
        String usuari = txtUser.getText();
        String contrasenya = txtPass.getText();
        String ipServidor = txtIPServidor.getText();

        boolean loginOk = mongoServeis.iniciarSessio(usuari, contrasenya);

        if (loginOk) {
            Client client = new Client(ipServidor, 7878);
            boolean usuariObtingut = client.obtenirUsuari(usuari);
            if (!usuariObtingut) {
                // Mostrar missatge d'error i tornar a mostrar la finestra de login
                JOptionPane.showMessageDialog(null, "Usuari ja loguejat o error de connexió", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Si no hi ha cap error, ocultar el panell de login i mostrar la finestra de xat
            SwingUtilities.getWindowAncestor(this).setVisible(false);
            Xat xat = new Xat(client);
            xat.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Nom d'usuari o contrasenya incorrectes", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void registrar(JTextFieldPersonalitzat txtUser, JTextFieldPassword txtPass) {
        String nomUsuari = txtUser.getText();
        String contrasenya = txtPass.getText();

        if (nomUsuari.isEmpty() || contrasenya.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Els camps d'usuari, correu electrònic i contrasenya no poden estar buits", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuari usuari = new Usuari(nomUsuari, contrasenya);

        try {
            boolean usuariExisteix;

            if (nomUsuari.equals("DAM")) {
                usuariExisteix = true;
            } else {
                usuariExisteix = mongoServeis.validarUsuari(nomUsuari);
            }

            if (usuariExisteix) {
                JOptionPane.showMessageDialog(null, "El nom d'usuari ja està en ús", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                mongoServeis.desarUsuari(usuari);
                JOptionPane.showMessageDialog(null, "Usuari registrat correctament");
            }
        } catch (HeadlessException ex) {
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
