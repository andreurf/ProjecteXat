package com.projecte.swing;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardUtil {

    public static String getClipboardContents() {
        try {
            return (String) Toolkit.getDefaultToolkit()
                                   .getSystemClipboard()
                                   .getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}