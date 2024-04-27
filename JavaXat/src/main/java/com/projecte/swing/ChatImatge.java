package com.projecte.swing;

import com.projecte.swing.components.CaixaFoto;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.Icon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Usuario
 */
public class ChatImatge extends javax.swing.JLayeredPane {

    public ChatImatge(boolean posicio) {
        initComponents();
        setLayout(new MigLayout("", "0[" + (posicio ? "right" : "left") + "]0", "3[]3"));
    }

    public void addImatge(Icon... imatges) {
        for (Icon imatge : imatges) {
            CaixaFoto pic = new CaixaFoto();
            pic.setPreferredSize(getAutoSize(imatge, 200, 200));
            pic.setImatge(imatge);
            add(pic, "wrap");
        }
    }

    private Dimension getAutoSize(Icon image, int w, int h) {
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        return new Dimension(width, height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
