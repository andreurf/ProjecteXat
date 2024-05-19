package com.projecte.swing;

import com.projecte.prova.Client;
import com.projecte.prova.MongoServeis;
import com.projecte.swing.components.ScrollBar;
import java.util.List;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralE extends javax.swing.JPanel {

    private final ChatTitol chatTitol;
    private final ChatBody chatBody;
    private final Client client;
    private ItemUsuaris damItem;

    public MenuLateralE(ChatTitol chatTitol, ChatBody chatBody, Client client) {
        initComponents();
        this.chatTitol = chatTitol;
        this.chatBody = chatBody;
        this.client = client;
        init();
    }

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "5[]5"));
        showGrup();
        selectDamGroup();
    }

    private void selectDamGroup() {
        if (damItem != null) {
            damItem.handleClick();
        }
    }

    private void showPersones() {
        menuList.removeAll();
        MongoServeis servidorMDB = new MongoServeis();
        String nomActual = Client.getNomUsuari();
        List<String> nomUsuaris = servidorMDB.obtindreNomsUsuaris(nomActual);
        for (String nom : nomUsuaris) {
            menuList.add(new ItemUsuaris(nom, chatTitol, chatBody, client), "wrap");
        }
        refrescarMenuList();
    }

    private void showGrup() {
        menuList.removeAll();
        damItem = new ItemUsuaris("DAM", chatTitol, chatBody, client);
        menuList.add(damItem, "wrap");
        refrescarMenuList();
    }

    private void refrescarMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuBoto1 = new com.projecte.swing.components.MenuBoto();
        menuBoto2 = new com.projecte.swing.components.MenuBoto();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setPreferredSize(new java.awt.Dimension(200, 7));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuBoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuMissatge.png")));
        menuBoto1.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/menuMissatgeBlau.png"))); // NOI18N
        menuBoto1.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/menuMissatge.png"))); // NOI18N
        menuBoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto1ActionPerformed(evt);
            }
        });
        menu.add(menuBoto1);

        menuBoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuGrup.png")));
        menuBoto2.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/menuGrupBlau.png")));
        menuBoto2.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/menuGrup.png")));
        menuBoto2.setSelected(true);
        menuBoto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto2ActionPerformed(evt);
            }
        });
        menu.add(menuBoto2);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuBoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto1ActionPerformed
        if (!menuBoto1.isSelected()) {
            menuBoto1.setSelected(true);
            menuBoto2.setSelected(false);
            showPersones();
        }
    }//GEN-LAST:event_menuBoto1ActionPerformed

    private void menuBoto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto2ActionPerformed
        if (!menuBoto2.isSelected()) {
            menuBoto1.setSelected(false);
            menuBoto2.setSelected(true);
            showGrup();
        }
    }//GEN-LAST:event_menuBoto2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.projecte.swing.components.MenuBoto menuBoto1;
    private com.projecte.swing.components.MenuBoto menuBoto2;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
