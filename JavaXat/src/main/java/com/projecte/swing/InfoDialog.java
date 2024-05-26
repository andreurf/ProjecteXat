package com.projecte.swing;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {

    public InfoDialog(Frame parent) {
        super(parent, "Informació sobre l'aplicació", true);
        initUI();
    }

    private void initUI() {
        // Defineix el text d'informació sobre l'aplicació
        String infoText = "<html><body>"
                + "<h2>Informació sobre l'aplicació</h2>"
                + "<p>Aquesta aplicació permet la comunicació entre usuaris a través de xats grupals i privats.</p>"
                + "<h3>Funcionalitats:</h3>"
                + "<ul>"
                + "<li>Xat grupal: Els usuaris poden unir-se a un xat grupal i intercanviar missatges.</li>"
                + "<li>Xats privats: Els usuaris poden iniciar xats privats amb altres usuaris.</li>"
                + "<li>Filtre de missatges per data: Pots filtrar els missatges per data per veure els xats passats.</li>"
                + "<li>Historial de xats: Pots veure l'historial de xats anteriors.</li>"
                + "</ul>"
                + "<h4>Per començar a xatejar si no tens usuari registrat.</h4>"
                + "<h4>Un cop registrat, logejat i ja pots xatejar.</h4>"
                + "</body></html>";

        // Crea un JLabel per mostrar el text d'informació
        JLabel label = new JLabel(infoText);
        label.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Afegir el JLabel al JDialog
        add(label);

        // Ajusta la mida del JDialog
        setSize(400, 380);
        setLocationRelativeTo(null);
    }
}