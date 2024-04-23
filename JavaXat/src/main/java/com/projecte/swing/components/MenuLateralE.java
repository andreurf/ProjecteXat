package com.projecte.swing.components;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralE extends javax.swing.JPanel {

    public MenuLateralE() {
        initComponents();
        init();
    }

    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "5[]5"));
        showPersones();
    }

    private void showPersones() {
        menuList.removeAll();
        for (int i = 0; i < 40; i++) {
            menuList.add(new ItemUsuaris("Persona " + i), "wrap");
        }
        refrescarMenuList();
    }
    
    private void showGrup(){
        menuList.removeAll();
        for (int i = 0; i < 4; i++) {
            menuList.add(new ItemUsuaris("Grup " + i), "wrap");
        }
        refrescarMenuList();
    }
    
    private void showEmail(){
        menuList.removeAll();
        for (int i = 0; i < 10; i++) {
            menuList.add(new ItemUsuaris("Email " + i), "wrap");
        }
        refrescarMenuList();
    }
    
    
    private void refrescarMenuList(){
        menuList.repaint();
        menuList.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuBoto1 = new com.projecte.swing.components.MenuBoto();
        menuBoto2 = new com.projecte.swing.components.MenuBoto();
        menuBoto3 = new com.projecte.swing.components.MenuBoto();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setPreferredSize(new java.awt.Dimension(200, 7));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuBoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuMissatgeBlau.png")));
        menuBoto1.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/menuMissatgeBlau.png"))); // NOI18N
        menuBoto1.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/menuMissatge.png"))); // NOI18N
        menuBoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto1ActionPerformed(evt);
            }
        });
        menu.add(menuBoto1);

        menuBoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuGrup.png")));
        menuBoto2.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/menuGrupBlau.png"))); // NOI18N
        menuBoto2.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/menuGrupBlau.png"))); // NOI18N
        menuBoto2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto2ActionPerformed(evt);
            }
        });
        menu.add(menuBoto2);

        menuBoto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/box.png")));
        menuBoto3.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/box_selected.png"))); // NOI18N
        menuBoto3.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/box.png"))); // NOI18N
        menuBoto3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBoto3ActionPerformed(evt);
            }
        });
        menu.add(menuBoto3);

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
            menuBoto3.setSelected(false);
            showPersones();
        }
    }//GEN-LAST:event_menuBoto1ActionPerformed

    private void menuBoto2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto2ActionPerformed
        if (!menuBoto2.isSelected()) {
            menuBoto1.setSelected(false);
            menuBoto2.setSelected(true);
            menuBoto3.setSelected(false);
            showGrup();
        }
    }//GEN-LAST:event_menuBoto2ActionPerformed

    private void menuBoto3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBoto3ActionPerformed
        if (!menuBoto3.isSelected()) {
            menuBoto1.setSelected(false);
            menuBoto2.setSelected(false);
            menuBoto3.setSelected(true);
            showEmail();
        }
    }//GEN-LAST:event_menuBoto3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.projecte.swing.components.MenuBoto menuBoto1;
    private com.projecte.swing.components.MenuBoto menuBoto2;
    private com.projecte.swing.components.MenuBoto menuBoto3;
    private javax.swing.JLayeredPane menuList;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
