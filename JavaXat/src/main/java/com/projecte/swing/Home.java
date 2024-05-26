package com.projecte.swing;

import com.projecte.serveis.Client;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class Home extends javax.swing.JLayeredPane {

    private Client client;
    private MenuLateralD menuLD;
    private MenuLateralE menuLE;
    private final XatBody xatBody = new XatBody();
    private final XatTitol xatTitol = new XatTitol();
    private XatBottom xatBottom;

    public Home(Client client) {
        this.client = client;
        this.xatBottom = new XatBottom(client, xatBody, xatTitol);
        initComponents();
        init();
    }

    public Home() {
        initComponents();
    }

    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        menuLD = new MenuLateralD();
        menuLE = new MenuLateralE(xatTitol, xatBody, client, menuLD);
        this.add(menuLE, "cell 0 0, grow");
        this.add(new XatText(xatTitol, xatBody, xatBottom, client), "cell 1 0, grow");
        this.add(menuLD, "cell 2 0, grow, wmin 200px, hmin 400px");
    }

    public void netejarXat() {
        xatBody.netejarMissatges();
    }

    public void engaxarText(String text) {
        xatBottom.setText(text);
    }

    public void enviarMissatge() {
        xatBottom.enviarMissatge();
    }
    
    public void changeTheme(boolean isDarkTheme){
        menuLE.changeTheme(isDarkTheme);
        menuLD.changeTheme(isDarkTheme);
        xatTitol.changeTheme(isDarkTheme);
        xatBody.changeTheme(isDarkTheme);
        xatBottom.changeTheme(isDarkTheme);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
