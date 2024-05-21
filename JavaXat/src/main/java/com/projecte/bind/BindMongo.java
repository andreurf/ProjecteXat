package com.projecte.bind;

import org.bson.Document;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BindMongo extends javax.swing.JFrame {

    private ConnexioMongo conector;
    private JTextField campIp;
    private JTextField campNomBaseDeDades;
    private JTextField campUsuari;
    private JPasswordField campContrasenya;
    private JComboBox<String> caixaColleccions;
    private JTable taula;
    private DefaultTableModel modelTaula;
    private List<Document> documents;

    public BindMongo() {
        conector = new ConnexioMongo();
        inicialitzarComponents();
    }

    @SuppressWarnings("unchecked")
    private void inicialitzarComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

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
        JButton botoEliminar = new JButton("Eliminar fila seleccionada");

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

        botoEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarFilaSeleccionada();
            }
        });

        modelTaula = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true; // Totes les cel·les són editables
            }
        };
        taula = new JTable(modelTaula);
        taula.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int fila = e.getFirstRow();
                    if (fila >= 0 && fila < documents.size()) {
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

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        panellEntrada.add(botoEliminar, c);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panellEntrada, BorderLayout.NORTH);
        getContentPane().add(panellDesplaçament, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Centrar la finestra
    }

    private void connectarAMongoDB() {
        String ip = campIp.getText().trim();
        String nomBaseDeDades = campNomBaseDeDades.getText().trim();
        String usuari = campUsuari.getText().trim();
        String contrasenya = new String(campContrasenya.getPassword()).trim();
        if (conector.connectar(ip, nomBaseDeDades, usuari, contrasenya)) {
            omplirCaixaColleccions();
        }
    }

    private void omplirCaixaColleccions() {
        List<String> colleccions = conector.obtenirNomsColleccions();
        caixaColleccions.removeAllItems();
        for (String colleccio : colleccions) {
            caixaColleccions.addItem(colleccio);
        }
    }

    private void carregarDadesColleccio() {
        String nomColleccio = (String) caixaColleccions.getSelectedItem();
        if (nomColleccio != null) {
            documents = conector.carregarColleccio(nomColleccio);
            if (documents.size() > 0) {
                Document primerDocument = documents.get(0);
                List<String> columnes = new ArrayList<>(primerDocument.keySet());

                modelTaula.setColumnIdentifiers(columnes.toArray());

                modelTaula.setRowCount(0); // Neteja les files existents
                for (Document doc : documents) {
                    List<Object> dadesFila = new ArrayList<>();
                    for (String col : columnes) {
                        dadesFila.add(doc.get(col));
                    }
                    modelTaula.addRow(dadesFila.toArray());
                }

                modelTaula.fireTableDataChanged();
            }
        }
    }

    private void actualitzarDocument(int fila) {
        String nomColleccio = (String) caixaColleccions.getSelectedItem();
        if (nomColleccio != null) {
            Document documentOriginal = documents.get(fila);

            Document documentActualitzat = new Document();
            for (int j = 0; j < modelTaula.getColumnCount(); j++) {
                documentActualitzat.append(modelTaula.getColumnName(j), modelTaula.getValueAt(fila, j));
            }

            conector.actualitzarDocument(nomColleccio, documentOriginal, documentActualitzat);
            documents.set(fila, documentActualitzat);
        }
    }

    private void eliminarFilaSeleccionada() {
        int filaSeleccionada = taula.getSelectedRow();
        if (filaSeleccionada != -1) {
            String nomColleccio = (String) caixaColleccions.getSelectedItem();
            if (nomColleccio != null) {
                Document documentAEliminar = documents.get(filaSeleccionada);
                Object id = documentAEliminar.get("_id");
                if (id != null) {
                    conector.eliminarDocumentPerId(nomColleccio, id);
                }
                documents.remove(filaSeleccionada);
                modelTaula.removeRow(filaSeleccionada);
                modelTaula.fireTableDataChanged();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Si us plau, selecciona una fila per eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
