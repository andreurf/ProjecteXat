package com.projecte.swing.components;

import javax.swing.JProgressBar;

/**
 *
 * @author Usuario
 */
public class Progress extends JProgressBar {

    public Progress() {
        setOpaque(false);
        setUI(new ProgressCircleUI(this));
    }
}
