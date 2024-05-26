package com.projecte.main;

import com.projecte.bind.BindMongo;
import com.projecte.swing.InfoDialog;
import com.projecte.swing.PanelLoginRegistre;
import com.projecte.swing.PanelPersonalitzat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Andreu i Quim
 */
public class LoginRegistre extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelPersonalitzat panelP;
    private PanelLoginRegistre loginRegistre;
    private boolean isLogin = false;
    private final double addSize = 30;
    private final double panelSize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private boolean isDarkTheme = false;

    public LoginRegistre() {
        initComponents();
        init();
    }

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/logoXat.png")).getImage());
        layout = new MigLayout("fill, insets 0");
        panelP = new PanelPersonalitzat();
        loginRegistre = new PanelLoginRegistre();
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fracioPanelP;
                double fracioLogin;
                double size = panelSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }

                if (isLogin) {
                    fracioPanelP = 1f - fraction;
                    fracioLogin = fraction;
                    if (fraction >= 0.5f) {
                        panelP.registreDreta(fracioPanelP * 100);
                    } else {
                        panelP.loginDreta(fracioLogin * 100);
                    }
                } else {
                    fracioPanelP = fraction;
                    fracioLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        panelP.registreEsquerra(fraction * 100);
                    } else {
                        panelP.loginEsquerra((1f - fraction) * 100);
                    }
                }

                if (fraction >= 0.5f) {
                    loginRegistre.mostrarRegistre(isLogin);
                }
                //fracioPanelP = Double.valueOf(df.format(fracioPanelP));
                fracioPanelP = Double.valueOf(df.format(fracioPanelP).replace(",", "."));
                fracioLogin = Double.valueOf(df.format(fracioLogin).replace(",", "."));
                layout.setComponentConstraints(panelP, "width " + size + "%, pos " + fracioPanelP + "al 0 n 100%");
                layout.setComponentConstraints(loginRegistre, "width " + loginSize + "%, pos " + fracioLogin + "al 0 n 100%");
                fons.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);
        fons.setLayout(layout);
        fons.add(panelP, "width " + panelSize + "%, pos 0al 0 n 100%");
        fons.add(loginRegistre, "width " + loginSize + "%, pos 1al 0 n 100%");
        panelP.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });
    }

    private void changeLookAndFeel() {
        isDarkTheme = !isDarkTheme;
        panelP.changeTheme(isDarkTheme);
        loginRegistre.changeTheme(isDarkTheme);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void showInfoDialog() {
        InfoDialog infoDialog = new InfoDialog(this);
        infoDialog.setVisible(true);
    }

    private void showUserGuide() {
        JDialog userGuideDialog = new JDialog(this, "Guia d'Usuari", true);
        userGuideDialog.setSize(600, 400);
        userGuideDialog.setLocationRelativeTo(this);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(getUserGuideContent()); // Método para obtener el contenido de la guía
        JScrollPane scrollPane = new JScrollPane(textArea);

        userGuideDialog.add(scrollPane);
        userGuideDialog.setVisible(true);
    }

    private String getUserGuideContent() {
        return "Guia d'Usuari:\n\n"
                + "1. Iniciar Sessió:\n"
                + "   - Per iniciar sessió, introdueix el teu nom d'usuari, contrasenya i la ip del servidor.\n"
                + "   - Fes clic al botó 'Inicia Sessió'.\n\n"
                + "2. Registre:\n"
                + "   - Per registrar-te, introdueix les teves dades personals.\n"
                + "   - Fes clic al botó 'Registrar-se'.\n\n"
                + "3. Xatejar:\n"
                + "   - Selecciona un usuari o un grup per començar a xatejar.\n"
                + "   - Escriu el teu missatge i fes clic a l'icona d'enviar o prem 'ctrl + Enter'.\n\n"
                + "4. Canviar Tema:\n"
                + "   - Per canviar el tema, ves a Opcions -> Canvia Tema.\n"
                + "   - Selecciona el tema desitjat.\n\n"
                + "5. Esborrar Dades:\n"
                + "   - Per esborar les dades del inputs, selecciona 'Esborrar dades' del menú 'Edita'.\n"
                + "6. Tancar Aplicacio:\n\n"
                + "   - Per tancar l'aplicacio, ves a Fitxer -> Sortir. o prem 'ctrl + Q'.\n\n"
                + "7. Informació sobre l'aplicacio:\n\n"
                + "   - Per obtindre mes informació sobre l'aplicacio, ves a Ajuda -> Info o prem 'ctrl + I'.\n";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fons = new javax.swing.JLayeredPane();
        barraEines = new javax.swing.JToolBar();
        botoTema = new javax.swing.JButton();
        botoInfo = new javax.swing.JButton();
        botoBind = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fons.setBackground(new java.awt.Color(255, 255, 255));
        fons.setOpaque(true);

        javax.swing.GroupLayout fonsLayout = new javax.swing.GroupLayout(fons);
        fons.setLayout(fonsLayout);
        fonsLayout.setHorizontalGroup(
            fonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        fonsLayout.setVerticalGroup(
            fonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        barraEines.setRollover(true);

        botoTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tema.png")));
        botoTema.setBorder(null);
        botoTema.setBorderPainted(false);
        botoTema.setFocusable(false);
        botoTema.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoTema.setMargin(new java.awt.Insets(0, 20, 0, 20));
        botoTema.setMinimumSize(new java.awt.Dimension(19, 19));
        botoTema.setPreferredSize(new java.awt.Dimension(19, 19));
        botoTema.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoTemaActionPerformed(evt);
            }
        });
        barraEines.add(botoTema);

        botoInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/info.png")));
        botoInfo.setBorder(null);
        botoInfo.setBorderPainted(false);
        botoInfo.setFocusable(false);
        botoInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoInfo.setMargin(new java.awt.Insets(0, 20, 0, 20));
        botoInfo.setMinimumSize(new java.awt.Dimension(19, 19));
        botoInfo.setPreferredSize(new java.awt.Dimension(19, 19));
        botoInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoInfoActionPerformed(evt);
            }
        });
        barraEines.add(botoInfo);

        botoBind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bind.png")));
        botoBind.setBorder(null);
        botoBind.setBorderPainted(false);
        botoBind.setFocusable(false);
        botoBind.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botoBind.setMargin(new java.awt.Insets(0, 20, 0, 20));
        botoBind.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botoBind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoBindActionPerformed(evt);
            }
        });
        barraEines.add(botoBind);

        jMenu1.setText("Fitxer");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem3.setText("Sortir");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Edita");

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem10.setText("Enganxa");
        jMenu5.add(jMenuItem10);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem11.setText("Esborra Dades");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Visualitza");

        jMenuItem6.setText("Barra d'eines");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem4.setText("Bind");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Opcions");

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem8.setText("Configuració");
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Canvia Tema");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Ajuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Guia Usuari");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem5.setText("Info");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fons)
            .addComponent(barraEines, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(barraEines, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(fons))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        changeLookAndFeel();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        BindMongo bindMongo = new BindMongo();
        bindMongo.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        loginRegistre.esborrarDades();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        showInfoDialog();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void botoTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoTemaActionPerformed
        changeLookAndFeel();
    }//GEN-LAST:event_botoTemaActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (barraEines.isVisible()) {
            barraEines.setVisible(false);
        } else {
            barraEines.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void botoInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoInfoActionPerformed
        showInfoDialog();
    }//GEN-LAST:event_botoInfoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        showUserGuide();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void botoBindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoBindActionPerformed
        BindMongo bindMongo = new BindMongo();
        bindMongo.setVisible(true);
    }//GEN-LAST:event_botoBindActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegistre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraEines;
    private javax.swing.JButton botoBind;
    private javax.swing.JButton botoInfo;
    private javax.swing.JButton botoTema;
    private javax.swing.JLayeredPane fons;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
