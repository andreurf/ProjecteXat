package com.projecte.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.PaintContext;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author andreu.reyfor.dam_in
 */
public class MenuBoto extends JButton{

    /**
     * @return the iconSimple
     */
    public Icon getIconSimple() {
        return iconSimple;
    }

    /**
     * @param iconSimple the iconSimple to set
     */
    public void setIconSimple(Icon iconSimple) {
        this.iconSimple = iconSimple;
    }

    /**
     * @return the iconSelected
     */
    public Icon getIconSelected() {
        return iconSelected;
    }

    /**
     * @param iconSelected the iconSelected to set
     */
    public void setIconSelected(Icon iconSelected) {
        this.iconSelected = iconSelected;
    }
    
    private Icon iconSimple;
    private Icon iconSelected;
    
    public MenuBoto() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    @Override
    public void setSelected(boolean b) {
        super.setSelected(b);
        if(b){
            setIcon(iconSelected);
        } else {
            setIcon(iconSimple);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()){
            g.setColor(new Color(110,213,255));
            g.fillRect(0, getHeight()-3, getWidth(), getHeight());
        }
    }
    
    
    
    
     
}
