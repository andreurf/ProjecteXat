package com.projecte.swing;

/**
 *
 * @author andreu i quim
 */
public class XatData extends javax.swing.JLayeredPane {

    public XatData() {
        initComponents();
    }
    
    public void setData(String data){
        lbData.setText(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbData = new javax.swing.JLabel();
        linia1 = new com.projecte.components.Linia();
        linia2 = new com.projecte.components.Linia();

        lbData.setForeground(new java.awt.Color(191, 191, 191));
        lbData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbData.setText("28/04/2024");

        linia1.setForeground(new java.awt.Color(191, 191, 191));

        linia2.setForeground(new java.awt.Color(191, 191, 191));

        setLayer(lbData, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(linia1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        setLayer(linia2, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
    private com.projecte.components.Linia linia1;
    private com.projecte.components.Linia linia2;
    // End of variables declaration//GEN-END:variables
}
