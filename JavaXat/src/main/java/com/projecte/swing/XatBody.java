package com.projecte.swing;

import com.projecte.components.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class XatBody extends javax.swing.JPanel {
    
    private boolean isDarkTheme = false;
    private final Color originalBodyBgColor = new Color(255, 255, 255);
    private final Color originalScrollBarBgColor = Color.WHITE;

    public XatBody() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar(isDarkTheme));
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public final void afegirItemE(String text, String usuari, String temps) {
        XatE item = new XatE();
        item.setText(text);
        item.setTemps(temps);
        item.setPerfilUsuari(usuari);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public final void afegirItemD(String text, String temps) {
        XatD item = new XatD();
        item.setText(text);
        body.add(item, "wrap, al right, w 100::80%");
        body.repaint();
        body.revalidate();
        item.setTemps(temps);
        scrollToBottom();
    }
    
    public void afegirEstat(String estat) {
        XatEstat item = new XatEstat();
        item.setEstat(estat);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void afegirData(String data) {
        XatData item = new XatData();
        item.setData(data);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void netejarMissatges() {
        body.removeAll();
        body.repaint();
        body.revalidate();
    }
    
    public void changeTheme(boolean isDarkTheme){
        this.isDarkTheme = isDarkTheme;
        Color bodyBgColor = isDarkTheme ? new Color(44, 44, 44) : originalBodyBgColor;
        Color scrollBarBgColor = isDarkTheme ? new Color(66, 66, 66) : originalScrollBarBgColor;
        
        setBackground(bodyBgColor);
        body.setBackground(bodyBgColor);
        sp.getViewport().setBackground(bodyBgColor);
        sp.setBackground(bodyBgColor);
        sp.getVerticalScrollBar().setBackground(scrollBarBgColor);

        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
