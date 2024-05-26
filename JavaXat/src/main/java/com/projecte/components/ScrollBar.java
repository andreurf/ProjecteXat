package com.projecte.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

/**
 *
 * @author andreu i quim
 */
public class ScrollBar extends JScrollBar {
    
    private boolean isDarkTheme;
    
    public ScrollBar(boolean isDarkTheme){
        this.isDarkTheme = isDarkTheme;
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5,5));
        setBackground(new Color(242,242,242,242));
        setUnitIncrement(20);
    }
    
    public void changeTheme(boolean isDarkTheme) {
        this.isDarkTheme = isDarkTheme;
        setUI();
    }

    private void setUI() {
        if (isDarkTheme) {
            setBackground(new Color(66, 66, 66));
        } else {
            setBackground(new Color(242, 242, 242));
        }
    }
    
}
