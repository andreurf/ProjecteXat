package com.projecte.swing.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

/**
 *
 * @author Usuario
 */
public class CaixaFoto extends JLayeredPane {

    public Icon getImatge() {
        return imatge;
    }

    public void setImatge(Icon imatge) {
        this.imatge = imatge;
    }

    private Icon imatge;

    @Override
    protected void paintComponent(Graphics g) {
        if (imatge != null) {
            Graphics2D g2 = (Graphics2D) g;
            Rectangle size = getAutoSize(imatge);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
           g2.drawImage(toImage(imatge), size.getLocation().x, size.getLocation().y, size.getSize().width, size.getSize().height, null);
        }
        super.paintComponents(g);
    }

    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        if(w>imatge.getIconWidth()){
            w=imatge.getIconWidth();
        }
        if(h>imatge.getIconHeight()){
            w=imatge.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = getWidth()/2-(width/2);
        int y = getHeight()/2-(height/2);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

}
