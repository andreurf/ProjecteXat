package com.projecte.swing;

import com.projecte.serveis.Client;
import com.projecte.serveis.Missatge;
import com.projecte.serveis.MongoServeis;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author andreu i quim
 */
public class ItemUsuaris extends javax.swing.JPanel implements CanviDataListener {

    private final XatTitol xatTitol;
    private final XatBody xatBody;
    private final Client client;
    private final MenuLateralD menuLD;
    private final MenuLateralE menuLE;
    private static String usuariSeleccionat;
    private boolean isDarkTheme = false;

    public ItemUsuaris(String nom, XatTitol xatTitol, XatBody xatBody, Client client, MenuLateralD menuLD, MenuLateralE menuLE) {
        initComponents();
        this.xatTitol = xatTitol;
        this.xatBody = xatBody;
        this.client = client;
        this.menuLD = menuLD;
        this.menuLE = menuLE;
        lbNom.setText(nom);
        ItemUsuaris itm = this;
        init();
        client.iniciarReceptorMissatges(xatBody, xatTitol, menuLE);
        menuLD.addDateChangeListener(itm);
    }

    private void init() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(isDarkTheme ? new java.awt.Color(60, 60, 60) : new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(isDarkTheme ? new Color(77, 77, 77) :new Color(242, 242, 242));
            }
        });
    }

    public void handleClick() {
        xatTitol.setNomUsuari(lbNom.getText());
        if (!lbNom.getText().equals("DAM")) {
            if (estatActiu.comprovarEstat()) {
                xatTitol.estatActiu();
            } else {
                xatTitol.setEstatText();
            }
        }
        usuariSeleccionat = lbNom.getText();
        refrescarMissatges();
        client.enviarMissatge(
                "/w " + client.getNomUsuari() + " " + lbNom.getText());
    }

    /**
     *
     * @param newDate
     */
    @Override
    public void canviData(Date newDate) {
        if (usuariSeleccionat != null && usuariSeleccionat.equals(lbNom.getText())) {
            refrescarMissatges();
        }
    }

    public void refrescarMissatges() {

        String nomUsuari = client.getNomUsuari();
        MongoServeis manager = MongoServeis.getInstance();
        List<Missatge> missatges;
        Date dataSeleccionada = menuLD.getDataSeleccionada();

        if (lbNom.getText().equals("DAM")) {
            missatges = manager.obtenirMissatgesPerGrupIData("DAM", dataSeleccionada);
        } else {
            missatges = manager.obtenirMissatgesPrivatsIData(nomUsuari, lbNom.getText(), dataSeleccionada);
            missatges.addAll(manager.obtenirMissatgesPrivatsIData(lbNom.getText(), nomUsuari, dataSeleccionada));
        }

        Collections.sort(missatges);

        xatBody.netejarMissatges();

        for (Missatge missatge : missatges) {
            if (missatge.getNomUsuari() != null && missatge.getNomUsuari().equals(nomUsuari)) {
                xatBody.afegirItemD(missatge.getMissatge(), missatge.getFormattedDateTime());
            } else if (missatge.getNomUsuari() != null) {
                xatBody.afegirItemE(missatge.getMissatge(), missatge.getNomUsuari(), missatge.getFormattedDateTime());
            }
        }

        xatBody.revalidate();
        xatBody.repaint();
    }

    public void setActiu(boolean active) {
        estatActiu.setActiu(active);
    }

    public void changeTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        Color backgroundColor = isDarkTheme ? new Color(77, 77, 77) : new java.awt.Color(242, 242, 242);
        Color textColor = isDarkTheme ? new java.awt.Color(255, 255, 255) : new java.awt.Color(0, 0, 0);

        setBackground(backgroundColor);
        
        lbNom.setForeground(textColor);
        estatActiu.setForeground(textColor);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imatgeAvatar1 = new com.projecte.components.ImatgeAvatar();
        lbNom = new javax.swing.JLabel();
        estatActiu = new com.projecte.components.EstatActiu();

        setBackground(new Color(242, 242, 242));

        imatgeAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/profile.png")));
        imatgeAvatar1.setBorderSize(0);

        lbNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imatgeAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNom, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(estatActiu, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(estatActiu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNom, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(imatgeAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.components.EstatActiu estatActiu;
    private com.projecte.components.ImatgeAvatar imatgeAvatar1;
    private javax.swing.JLabel lbNom;
    // End of variables declaration//GEN-END:variables
}
