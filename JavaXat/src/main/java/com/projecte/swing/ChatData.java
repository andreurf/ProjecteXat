package com.projecte.swing;

/**
 *
 * @author Usuario
 */
public class ChatData extends javax.swing.JLayeredPane {

    public ChatData() {
        initComponents();
    }
    
    public void setData(String data){
        lbData.setText(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbData = new javax.swing.JLabel();
        linia1 = new com.projecte.swing.components.Linia();
        linia2 = new com.projecte.swing.components.Linia();

        lbData.setForeground(new java.awt.Color(191, 191, 191));
        lbData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbData.setText("28/04/2024");

        linia1.setForeground(new java.awt.Color(191, 191, 191));

        linia2.setForeground(new java.awt.Color(191, 191, 191));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(linia1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linia2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(linia2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbData;
    private com.projecte.swing.components.Linia linia1;
    private com.projecte.swing.components.Linia linia2;
    // End of variables declaration//GEN-END:variables
}
