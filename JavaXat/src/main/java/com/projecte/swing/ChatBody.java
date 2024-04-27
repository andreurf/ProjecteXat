package com.projecte.swing;

import com.projecte.swing.components.ScrollBar;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class ChatBody extends javax.swing.JPanel {

    public ChatBody() {
        initComponents();
        init();
        addItemD("Send a text message to a group of contacts. Include photos, personalize your texts, and track who clicked your links.",  new ImageIcon(getClass().getResource("/testing/suri.jpg")), new ImageIcon(getClass().getResource("/testing/mona.jpg")));
        addItemD("hello\nHi");
        addItemE("Simpletext started as a passion project because I couldn’t find what I was looking for. Most apps were trying to do too much and ended up bloated with features I don’t need. So I built Simpletext based on a simple premise — what if there’s an app that refuses to do more, choosing instead to do just one thing, and do it well? For Simpletext, that one thing is writing.", "Raven", new ImageIcon(getClass().getResource("/testing/dog.jpg")), new ImageIcon(getClass().getResource("/testing/dog.jpg")));
        addData("28/04/2024");
        addItemE("hello\nerererew\newewe", "Dara");
        addItemD("hello\nerererew\newewe", new ImageIcon(getClass().getResource("/testing/suri.jpg")));
        addItemE("hello\nerererew\newewe", "Jonh", new ImageIcon(getClass().getResource("/testing/dog.jpg")), new ImageIcon(getClass().getResource("/testing/dog.jpg")));
        addData("Today");
        addItemE("", "Ro", new ImageIcon(getClass().getResource("/testing/suri.jpg")));
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public final void addItemE(String text, String usuari, Icon... imatge) {
        ChatEPerfil item = new ChatEPerfil();
        item.setText(text);
        item.setImatge(imatge);
        item.setTime();
        item.setPerfilUsuari(usuari);
        body.add(item, "wrap, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public final void addItemD(String text, Icon... imatge) {
        ChatD item = new ChatD();
        item.setText(text);
        item.setImatge(imatge);
        body.add(item, "wrap, al right, w 100::80%");
        body.repaint();
        body.revalidate();
    }

    public void addData(String data) {
        ChatData item = new ChatData();
        item.setData(data);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
