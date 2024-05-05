package com.projecte.swing;

import com.projecte.event.EventXat;
import com.projecte.event.PublicEvent;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class ChatText extends javax.swing.JPanel {

    private ChatTitol chatTitol;
    
    public ChatText(ChatTitol chatTitol) {
        initComponents();
        this.chatTitol = chatTitol;
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx", "0[fill]0","0[]0[100%, bottom]0[shrink 0]0"));
        ChatBody chatBody = new ChatBody();
        ChatBottom chatBottom = new ChatBottom();
        PublicEvent.getInstance().addEventXat(new EventXat() {
            @Override
            public void enviarMissatge(String missatge) {
                chatBody.addItemD(missatge);
            }
        });
        add(chatTitol,"wrap");
        add(chatBody,"wrap");
        add(chatBottom,"h :: 50%");
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
