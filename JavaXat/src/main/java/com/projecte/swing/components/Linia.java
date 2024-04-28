package com.projecte.swing.components;

import java.awt.Graphics;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class Linia extends JLabel{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getForeground());
        g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
    }
    
    
}
