package com.projecte.swing;

import com.projecte.serveis.Client;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class XatText extends javax.swing.JPanel {

    private final XatTitol xatTitol;
    private final XatBody xatBody;
    private XatBottom xatBottom;
    private final Client client;
    
    public XatText(XatTitol xatTitol, XatBody xatBody, XatBottom xatBottom, Client client) {
        initComponents();
        this.xatTitol = xatTitol;
        this.xatBody = xatBody;
        this.xatBottom = xatBottom;
        this.client = client;
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx", "0[fill]0","0[]0[100%, bottom]0[shrink 0]0"));
        add(xatTitol,"wrap");
        add(xatBody,"wrap");
        add(xatBottom,"h :: 50%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(727, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
