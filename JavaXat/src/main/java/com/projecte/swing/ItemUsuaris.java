package com.projecte.swing;

import com.projecte.serveis.Client;
import com.projecte.serveis.Missatge;
import com.projecte.serveis.MongoServeis;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author andreu i quim
 */
public class ItemUsuaris extends javax.swing.JPanel implements DateChangeListener {

    private ChatTitol chatTitol;
    private ChatBody chatBody;
    private Client client;
    private MenuLateralD menuLD;
    private MenuLateralE menuLE;
    private static String selectedUser;

    public ItemUsuaris(String nom, ChatTitol chatTitol, ChatBody chatBody, Client client, MenuLateralD menuLD, MenuLateralE menuLE) {
        initComponents();
        this.chatTitol = chatTitol;
        this.chatBody = chatBody;
        this.client = client;
        this.menuLD = menuLD;
        this.menuLE = menuLE;
        lbNom.setText(nom);
        init();
        client.iniciarReceptorMissatges(chatBody, chatTitol, menuLE); // Iniciar el receptor de mensajes en un hilo separado
        menuLD.addDateChangeListener(this);
    }

    private void init() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(242, 242, 242));
            }
        });
    }

    public void handleClick() {
        chatTitol.setNomUsuari(lbNom.getText());
        if (!lbNom.getText().equals("DAM")) {
            if (activeStatus.isActive()) {
                chatTitol.estatActiu();
            } else {
                chatTitol.setStatusText("Inactiu");
            }
        }

//        chatBody.limpiarMensajes();
        selectedUser = lbNom.getText();

        refrescarMensajes();

        client.enviarMissatge(
                "/w " + client.getNomUsuari() + " " + lbNom.getText());
    }

    @Override
    public void onDateChanged(Date newDate) {
        if (selectedUser != null && selectedUser.equals(lbNom.getText())) {
            refrescarMensajes();
        }
    }

    public void refrescarMensajes() {

        String nomUsuari = client.getNomUsuari();
        MongoServeis manager = MongoServeis.getInstance();
        List<Missatge> missatges;
        Date selectedDate = menuLD.getSelectedDate();

        if (lbNom.getText().equals("DAM")) {
            missatges = manager.obtenirMissatgesPerGrupIData("DAM", selectedDate);
        } else {
            missatges = manager.obtenirMissatgesPrivatsIData(nomUsuari, lbNom.getText(), selectedDate);
            missatges.addAll(manager.obtenirMissatgesPrivatsIData(lbNom.getText(), nomUsuari, selectedDate));
        }

        Collections.sort(missatges);

        chatBody.limpiarMensajes();

        for (Missatge missatge : missatges) {
            if (missatge.getNomUsuari() != null && missatge.getNomUsuari().equals(nomUsuari)) {
                chatBody.addItemD(missatge.getMissatge(), missatge.getFormattedDateTime());
            } else if (missatge.getNomUsuari() != null) {
                chatBody.addItemE(missatge.getMissatge(), missatge.getNomUsuari(), missatge.getFormattedDateTime());
            }
        }

        chatBody.revalidate();
        chatBody.repaint();
    }

    public void setActive(boolean active) {
        activeStatus.setActive(active);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imatgeAvatar1 = new com.projecte.swing.components.ImatgeAvatar();
        lbNom = new javax.swing.JLabel();
        activeStatus = new com.projecte.swing.components.ActiveStatus();

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
                .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbNom, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(imatgeAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.swing.components.ActiveStatus activeStatus;
    private com.projecte.swing.components.ImatgeAvatar imatgeAvatar1;
    private javax.swing.JLabel lbNom;
    // End of variables declaration//GEN-END:variables
}
