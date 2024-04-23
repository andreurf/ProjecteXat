package com.projecte.swing.components;

/**
 *
 * @author andreu i quim
 */
public class ChatText extends javax.swing.JPanel {

    public ChatText() {
        initComponents();
        init();
    }
    
    private void init(){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatTitol = new com.projecte.swing.components.ChatTitol();
        chatBottom = new com.projecte.swing.components.ChatBottom();
        chatBody = new com.projecte.swing.components.ChatBody();

        setBackground(new java.awt.Color(249, 249, 249));
        setPreferredSize(new java.awt.Dimension(150, 600));

        javax.swing.GroupLayout chatTitolLayout = new javax.swing.GroupLayout(chatTitol);
        chatTitol.setLayout(chatTitolLayout);
        chatTitolLayout.setHorizontalGroup(
            chatTitolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chatTitolLayout.setVerticalGroup(
            chatTitolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout chatBottomLayout = new javax.swing.GroupLayout(chatBottom);
        chatBottom.setLayout(chatBottomLayout);
        chatBottomLayout.setHorizontalGroup(
            chatBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chatBottomLayout.setVerticalGroup(
            chatBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatTitol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chatBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(chatBody, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chatTitol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(chatBody, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(chatBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.swing.components.ChatBody chatBody;
    private com.projecte.swing.components.ChatBottom chatBottom;
    private com.projecte.swing.components.ChatTitol chatTitol;
    // End of variables declaration//GEN-END:variables
}
