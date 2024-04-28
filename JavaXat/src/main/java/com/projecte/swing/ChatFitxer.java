package com.projecte.swing;

/**
 *
 * @author Usuario
 */
public class ChatFitxer extends javax.swing.JPanel {

    public ChatFitxer() {
        initComponents();
        setOpaque(false);
    }

    public void setFitxer(String nomFitxer, String tamanyFitxer){
        lbNomFitxer.setText(nomFitxer);
        lbTamanyFitxer.setText(tamanyFitxer);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progress1 = new com.projecte.swing.components.Progress();
        jPanel1 = new javax.swing.JPanel();
        lbNomFitxer = new javax.swing.JLabel();
        lbTamanyFitxer = new javax.swing.JLabel();

        progress1.setBorder(null);
        progress1.setProgressType(com.projecte.swing.components.Progress.ProgressType.FILE);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        lbNomFitxer.setText("Nom del fitxer.file");
        jPanel1.add(lbNomFitxer);

        lbTamanyFitxer.setForeground(new java.awt.Color(7, 99, 153));
        lbTamanyFitxer.setText("5 MB");
        jPanel1.add(lbTamanyFitxer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(progress1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progress1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbNomFitxer;
    private javax.swing.JLabel lbTamanyFitxer;
    private com.projecte.swing.components.Progress progress1;
    // End of variables declaration//GEN-END:variables
}
