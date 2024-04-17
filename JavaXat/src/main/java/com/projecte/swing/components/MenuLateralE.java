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
    
    private void init(){
        menuList.setLayout(new MigLayout("fillx","0[]0","5[]5"));
        showPersones();
    }

    private void showPersones() {
        for (int i = 0; i < 8; i++) {
            menuList.add(new ItemUsuaris("Persona " + i) , "wrap");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuBoto1 = new com.projecte.swing.components.MenuBoto();
        menuBoto2 = new com.projecte.swing.components.MenuBoto();
        menuBoto3 = new com.projecte.swing.components.MenuBoto();
        menuList = new javax.swing.JLayeredPane();

        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setPreferredSize(new java.awt.Dimension(200, 7));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuBoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuMissatgeBlau.png")));
        menu.add(menuBoto1);

        menuBoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuGrup.png")));
        menu.add(menuBoto2);

        menuBoto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/box.png")));
        menu.add(menuBoto3);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuList)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuList)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.projecte.swing.components.MenuBoto menuBoto1;
    private com.projecte.swing.components.MenuBoto menuBoto2;
    private com.projecte.swing.components.MenuBoto menuBoto3;
    private javax.swing.JLayeredPane menuList;
    // End of variables declaration//GEN-END:variables
}
