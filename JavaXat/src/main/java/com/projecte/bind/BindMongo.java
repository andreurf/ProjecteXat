package com.projecte.bind;

import com.mongodb.client.MongoIterable;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.bson.Document;

public class BindMongo extends javax.swing.JFrame {

    private final MongoDBConnector connector;
    private JTextField campIp;
    private JTextField campNomBaseDeDades;
    private JTextField campUsuari;
    private JPasswordField campContrasenya;
    private JComboBox<String> caixaColleccions;
    private JTable taula;
    private ModelTaulaMongoDB modelTaula;

    public BindMongo() {
        connector = new MongoDBConnector();
        inicialitzarComponents();
    }

    @SuppressWarnings("unchecked")
    private void inicialitzarComponents() {
        // Configura la ventana para deshabilitar el botón de cerrar
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        
        JLabel etiquetaIp = new JLabel("IP del servidor:");
        campIp = new JTextField(15);
        JLabel etiquetaNomBaseDeDades = new JLabel("Nom de la base de dades:");
        campNomBaseDeDades = new JTextField(20);
        JLabel etiquetaUsuari = new JLabel("Usuari:");
        campUsuari = new JTextField(15);
        JLabel etiquetaContrasenya = new JLabel("Contrasenya:");
        campContrasenya = new JPasswordField(15);
        JButton botoConnectar = new JButton("Connectar");
        caixaColleccions = new JComboBox<>();
        JButton botoCarregar = new JButton("Carregar col·lecció");

        botoConnectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectarAMongoDB();
            }
        });

        botoCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarDadesColleccio();
            }
        });

        taula = new JTable();
        taula.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow();
                    if (fila >= 0) {
                        actualitzarDocument(fila);
                    }
                }
            }
        });

        JScrollPane panellDesplaçament = new JScrollPane(taula);

        JPanel panellEntrada = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        panellEntrada.add(etiquetaIp, c);
        c.gridx = 1;
        panellEntrada.add(campIp, c);

        c.gridx = 0;
        c.gridy = 1;
        panellEntrada.add(etiquetaNomBaseDeDades, c);
        c.gridx = 1;
        panellEntrada.add(campNomBaseDeDades, c);

        c.gridx = 0;
        c.gridy = 2;
        panellEntrada.add(etiquetaUsuari, c);
        c.gridx = 1;
        panellEntrada.add(campUsuari, c);

        c.gridx = 0;
        c.gridy = 3;
        panellEntrada.add(etiquetaContrasenya, c);
        c.gridx = 1;
        panellEntrada.add(campContrasenya, c);

        c.gridx = 0;
        c.gridy = 4;
        panellEntrada.add(botoConnectar, c);
        c.gridx = 1;
        panellEntrada.add(caixaColleccions, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        panellEntrada.add(botoCarregar, c);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panellEntrada, BorderLayout.NORTH);
        getContentPane().add(panellDesplaçament, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void connectarAMongoDB() {
        String ip = campIp.getText().trim();
        String nomBaseDeDades = campNomBaseDeDades.getText().trim();
        String usuari = campUsuari.getText().trim();
        String contrasenya = new String(campContrasenya.getPassword()).trim();
        if (!ip.isEmpty() && !nomBaseDeDades.isEmpty() && !usuari.isEmpty() && !contrasenya.isEmpty()) {
            try {
                connector.connectar(ip, nomBaseDeDades, usuari, contrasenya);
                omplirCaixaColleccions();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error d'autenticació: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Si us plau, emplena tots els camps: IP del servidor, nom de la base de dades, usuari i contrasenya.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void omplirCaixaColleccions() {
        MongoIterable<String> colleccions = connector.obtenirColleccions();
        caixaColleccions.removeAllItems();
        for (String colleccio : colleccions) {
            caixaColleccions.addItem(colleccio);
        }
    }

    private void carregarDadesColleccio() {
        String nomColleccio = (String) caixaColleccions.getSelectedItem();
        if (nomColleccio != null) {
            List<Document> documents = connector.carregarDadesColleccio(nomColleccio);
            modelTaula = new ModelTaulaMongoDB(documents);
            taula.setModel(modelTaula);
        }
    }

    private void actualitzarDocument(int fila) {
        String nomColleccio = (String) caixaColleccions.getSelectedItem();
        if (nomColleccio != null) {
            Document documentOriginal = modelTaula.getDocument(fila);
            Document documentActualitzat = new Document();
            for (int j = 0; j < modelTaula.getColumnCount(); j++) {
                documentActualitzat.append(modelTaula.getColumnName(j), modelTaula.getValueAt(fila, j));
            }
            connector.actualitzarDocument(nomColleccio, documentOriginal, documentActualitzat);
            modelTaula.setDocument(fila, documentActualitzat);
        }
    }

}
