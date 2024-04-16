package com.projecte.swing.components;

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
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuBoto1 = new com.projecte.swing.components.MenuBoto();
        menuBoto2 = new com.projecte.swing.components.MenuBoto();
        menuBoto3 = new com.projecte.swing.components.MenuBoto();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(200, 600));

        menu.setPreferredSize(new java.awt.Dimension(200, 7));
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuBoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuMissatgeBlau.png")));
        menu.add(menuBoto1);

        menuBoto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuGrup.png")));
        menu.add(menuBoto2);

        menuBoto3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/box.png")));
        menu.add(menuBoto3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 560, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private com.projecte.swing.components.MenuBoto menuBoto1;
    private com.projecte.swing.components.MenuBoto menuBoto2;
    private com.projecte.swing.components.MenuBoto menuBoto3;
    // End of variables declaration//GEN-END:variables
}
