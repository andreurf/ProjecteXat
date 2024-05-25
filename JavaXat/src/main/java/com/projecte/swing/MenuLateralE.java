package com.projecte.swing;

import com.projecte.bind.BindMongo;
import com.projecte.serveis.Client;
import com.projecte.serveis.MongoServeis;
import com.projecte.components.ScrollBar;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralE extends javax.swing.JPanel {

    private final XatTitol xatTitol;
    private final XatBody xatBody;
    private final Client client;
    private ItemUsuaris damItem;
    private final MenuLateralD menuLD;
    private final Map<String, ItemUsuaris> usuarisMap = new HashMap<>();
    private final Map<String, Boolean> usuariEstatMap = new HashMap<>();

    public MenuLateralE(XatTitol xatTitol, XatBody xatBody, Client client, MenuLateralD menuLD) {
        initComponents();
        this.xatTitol = xatTitol;
        this.xatBody = xatBody;
        this.client = client;
        this.menuLD = menuLD;
        init();
    }

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        llistaMenu.setLayout(new MigLayout("fillx", "0[]0", "5[]5"));
        mostrarLlistaGrup();
        activarGrupDam();
    }

    private void activarGrupDam() {
        if (damItem != null) {
            damItem.handleClick();
        }
    }

    private void mostrarLlistaUsuaris() {
        llistaMenu.removeAll();
        MongoServeis servidorMDB = MongoServeis.getInstance();
        String nomActual = Client.getNomUsuari();
        List<String> nomUsuaris = servidorMDB.obtindreNomsUsuaris(nomActual);
        for (String nom : nomUsuaris) {
            ItemUsuaris item = new ItemUsuaris(nom, xatTitol, xatBody, client, menuLD, this);
            llistaMenu.add(item, "wrap");
            usuarisMap.put(nom, item);
            
            Boolean actiu = usuariEstatMap.get(nom);
            if (actiu != null) {
                item.setActiu(actiu);
            }
        }
        refrescarLlista();
    }

    private void mostrarLlistaGrup() {
        llistaMenu.removeAll();
        damItem = new ItemUsuaris("DAM", xatTitol, xatBody, client, menuLD, this);
        llistaMenu.add(damItem, "wrap");
        refrescarLlista();
    }
    
    private void eliminarLlista() {
        llistaMenu.removeAll();
        refrescarLlista();
    }

    private void refrescarLlista() {
        llistaMenu.repaint();
        llistaMenu.revalidate();
    }
    
    public void setActiu(String username, boolean active) {
        ItemUsuaris itemUsuari = usuarisMap.get(username);
        if (itemUsuari != null) {
            itemUsuari.setActiu(active);
        }
        usuariEstatMap.put(username, active);
        System.out.println("El usuari " + username + " s'ha conectat");
    }
    
    private ImageIcon carregarIcona(String path) {
        URL resource = getClass().getResource(path);
        if (resource != null) {
            return new ImageIcon(resource);
        } else {
            System.err.println("Resource not found: " + path);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuBoto1 = new com.projecte.components.MenuBoto();
        menuBoto2 = new com.projecte.components.MenuBoto();
        menuBoto3 = new com.projecte.components.MenuBoto();
        sp = new javax.swing.JScrollPane();
        llistaMenu = new javax.swing.JLayeredPane();

        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setPreferredSize(new java.awt.Dimension(200, 7));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuBoto1.setIcon(carregarIcona("/menuMissatge.png"));
        menuBoto1.setIconSelected(carregarIcona("/menuMissatgeBlau.png")); // NOI18N
        menuBoto1.setIconSimple(carregarIcona("/menuMissatge.png")); // NOI18N
        menuBoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto1ActionPerformed(evt);
            }
        });
        menu.add(menuBoto1);

        menuBoto2.setIcon(carregarIcona("/menuGrup.png"));
        menuBoto2.setIconSelected(carregarIcona("/menuGrupBlau.png"));
        menuBoto2.setIconSimple(carregarIcona("/menuGrup.png"));
        menuBoto2.setSelected(true);
        menuBoto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto2ActionPerformed(evt);
            }
        });
        menu.add(menuBoto2);

        menuBoto3.setIcon(carregarIcona("/bindGris.png"));
        menuBoto3.setIconSelected(carregarIcona("/bind.png")); // NOI18N
        menuBoto3.setIconSimple(carregarIcona("/bindGris.png")); // NOI18N
        menuBoto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto3ActionPerformed(evt);
            }
        });
        menu.add(menuBoto3);

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout llistaMenuLayout = new javax.swing.GroupLayout(llistaMenu);
        llistaMenu.setLayout(llistaMenuLayout);
        llistaMenuLayout.setHorizontalGroup(
            llistaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        llistaMenuLayout.setVerticalGroup(
            llistaMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        sp.setViewportView(llistaMenu);

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
            menuBoto3.setSelected(false);
            mostrarLlistaUsuaris();
        }
    }//GEN-LAST:event_menuBoto1ActionPerformed

    private void menuBoto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto2ActionPerformed
        if (!menuBoto2.isSelected()) {
            menuBoto1.setSelected(false);
            menuBoto2.setSelected(true);
            menuBoto3.setSelected(false);
            mostrarLlistaUsuaris();
        }
    }//GEN-LAST:event_menuBoto2ActionPerformed

    private void menuBoto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto3ActionPerformed
        if (!menuBoto3.isSelected()) {
            menuBoto1.setSelected(false);
            menuBoto2.setSelected(false);
            menuBoto3.setSelected(true);
            eliminarLlista();
            BindMongo bindMongo = new BindMongo();
            bindMongo.setVisible(true);
        }

    }//GEN-LAST:event_menuBoto3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane llistaMenu;
    private javax.swing.JLayeredPane menu;
    private com.projecte.components.MenuBoto menuBoto1;
    private com.projecte.components.MenuBoto menuBoto2;
    private com.projecte.components.MenuBoto menuBoto3;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
