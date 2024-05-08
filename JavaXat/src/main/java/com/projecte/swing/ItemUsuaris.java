package com.projecte.swing;

import com.projecte.prova.ClientProva;
import com.projecte.prova.MissatgeModelProva;
import com.projecte.prova.MongoDBManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;



/**
 *
 * @author andreu i quim
 */
public class ItemUsuaris extends javax.swing.JPanel {

    private ChatTitol chatTitol;
    private ChatBody chatBody;

    public ItemUsuaris(String nom, ChatTitol chatTitol, ChatBody chatBody) {
        initComponents();
        this.chatTitol = chatTitol;
        this.chatBody = chatBody;
        lbNom.setText(nom);
        init();
    }

    private void init() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                chatTitol.setNomUsuari(lbNom.getText());
                chatBody.limpiarMensajes();
                String nomUsuari = ClientProva.getNomUsuari();
                MongoDBManager manager = new MongoDBManager();
                List<MissatgeModelProva> missatges = manager.obtenirMissatgesPerGrup("DAM");
                for (MissatgeModelProva missatge : missatges) {
                    if (missatge.getNomUsuari().equals(nomUsuari)) {
                        chatBody.addItemD(missatge.getMissatge());
                    } else {
                        chatBody.addItemE(missatge.getMissatge(), missatge.getNomUsuari());
                    }
                }
                chatBody.revalidate();
                chatBody.repaint();
                /*List<MissatgeModelProva> missatges = manager.obtenirMissatgesPerUsuari(nomUsuari);
                for (MissatgeModelProva missatge : missatges) {
                    if (missatge.getNomUsuari().equals(nomUsuari)) {
                        chatBody.addItemD(missatge.getMissatge());
                        System.out.println(missatge.getMissatge());
                    } else {
                        chatBody.addItemE(missatge.getMissatge(), "Others");
                        System.out.println(missatge.getMissatge());
                    }
                }*/
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230, 230, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(242, 242, 242));
            }

        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imatgeAvatar1 = new com.projecte.swing.components.ImatgeAvatar();
        lbNom = new javax.swing.JLabel();

        setBackground(new Color(242, 242, 242));

        imatgeAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/profile.png")));
        imatgeAvatar1.setBorderSize(0);

        lbNom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imatgeAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbNom, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNom, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(imatgeAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.projecte.swing.components.ImatgeAvatar imatgeAvatar1;
    private javax.swing.JLabel lbNom;
    // End of variables declaration//GEN-END:variables
}
