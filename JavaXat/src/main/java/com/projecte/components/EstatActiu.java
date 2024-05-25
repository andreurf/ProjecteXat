package com.projecte.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class EstatActiu extends Component {
    
    private boolean estat;
    
    public boolean comprovarEstat() {
        return estat;
    }

    public void setActiu(boolean estat) {
        this.estat = estat;
        repaint();
    }

    public EstatActiu() {
        setPreferredSize(new Dimension(8, 8));
    }

    @Override
    public void paint(Graphics grphcs) {
        if (estat) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(62, 165, 49));
            g2.fillOval(0, (getHeight() / 2) - 4, 8, 8);
        }
    }
}