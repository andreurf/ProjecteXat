package com.projecte.swing;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.projecte.swing.components.PanelLoginRegistre;
import com.projecte.swing.components.PanelPersonalitzat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Andreu i Quim
 */
public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelPersonalitzat panelP;
    private PanelLoginRegistre loginRegistre;
    private boolean isLogin = false;
    private final double addSize = 30;
    private final double panelSize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public Main() {
        initComponents();
        init();
    }

    private void init() {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fons = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fons, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fons)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane fons;
    // End of variables declaration//GEN-END:variables
}
