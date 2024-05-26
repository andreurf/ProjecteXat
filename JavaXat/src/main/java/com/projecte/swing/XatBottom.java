package com.projecte.swing;

import com.projecte.serveis.Client;
import com.projecte.components.JIMSendTextPane;
import com.projecte.components.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author andreu i quim
 */
public class XatBottom extends javax.swing.JPanel {

    private final Client client;
    private final XatBody xatBody;
    private final XatTitol xatTitol;
    private JIMSendTextPane txt;
    private boolean isDarkTheme = false;
    private final Color originalBackgroundColor = new Color(242, 242, 242);

    public XatBottom(Client client, XatBody xatBody, XatTitol xatTitol) {
        this.client = client;
        this.xatBody = xatBody;
        this.xatTitol = xatTitol;
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                refrescar();
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    enviarMissatge();
                }
            }
        });
        txt.setHintText("Escriu un missatge aqui...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar(isDarkTheme);
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enviarMissatge();
            }
        });
        cmd.setFocusPainted(false);
        panel.add(cmd);
        add(panel);
    }
    
    public void enviarMissatge() {
        String text = txt.getText().trim();
        if (!text.equals("")) {
            if (xatTitol.getLbNom().equals("DAM")) {
                client.enviarMissatge(text);
            } else {
                client.enviarMissatge("/p " + xatTitol.getLbNom() + " " + text);
            }
            txt.setText("");
            txt.grabFocus();
        } else {
            txt.grabFocus();
        }
        refrescar();
    }
    
    public void setText(String text){
        if(txt != null){
            txt.setText(text);
        }
        refrescar();
    }

    private void refrescar() {
        revalidate();
    }
    
    public void changeTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        Color backgroundColor = isDarkTheme ? new Color(70, 70, 70) : originalBackgroundColor;
        Color textColor = isDarkTheme ? Color.WHITE : Color.BLACK;

        setBackground(backgroundColor);
        txt.setBackground(backgroundColor);
        txt.setForeground(textColor);

        for (Component comp : getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                scrollPane.getViewport().setBackground(backgroundColor);
                scrollPane.setVerticalScrollBar(new ScrollBar(isDarkTheme));
            } else if (comp instanceof JPanel) {
                comp.setBackground(backgroundColor);
            }
        }

        revalidate();
        repaint();
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
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
