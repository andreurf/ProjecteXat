package com.projecte.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author andreu i quim
 */
public class ScrollBar extends JScrollBar {
    
    public ScrollBar(){
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5,5));
        setBackground(new Color(242,242,242,242));
        setUnitIncrement(20);
    }
    
}
