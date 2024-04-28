package com.projecte.swing;

import com.projecte.event.PublicEvent;
import com.projecte.swing.components.CaixaFoto;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.SwingUtilities;
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
            CaixaFoto foto = new CaixaFoto();
            foto.setPreferredSize(getAutoSize(imatge, 200, 200));
            foto.setImatge(imatge);
            addEvent(foto, imatge);
            add(foto, "wrap");
        }
    }
    
    public void addImatge(String... imatges) {
        for (String imatge : imatges) {
            ItemImatge foto = new ItemImatge();
            foto.setPreferredSize(new Dimension(200,200));
            foto.setImatge(imatge);
            //addEvent(foto, imatge);
            add(foto, "wrap");
        }
    }
    
    private void addEvent(Component com, Icon imatge){
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    PublicEvent.getInstance().getEventImatgeVista().vistaImatge(imatge);
                }
            }
            
        });
    }

    private Dimension getAutoSize(Icon imatge, int w, int h) {
        if(w>imatge.getIconWidth()){
            w=imatge.getIconWidth();
        }
        if(h>imatge.getIconHeight()){
            w=imatge.getIconHeight();
        }
        int iw = imatge.getIconWidth();
        int ih = imatge.getIconHeight();
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
