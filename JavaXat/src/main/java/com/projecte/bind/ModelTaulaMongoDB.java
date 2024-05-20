package com.projecte.bind;

import javax.swing.table.AbstractTableModel;
import org.bson.Document;
import java.util.List;
import java.util.ArrayList;

public class ModelTaulaMongoDB extends AbstractTableModel {

    private List<Document> documents;
    private List<String> columnes;

    public ModelTaulaMongoDB(List<Document> documents) {
        this.documents = documents;
        if (!documents.isEmpty()) {
            columnes = new ArrayList<>(documents.get(0).keySet());
        }
    }

    @Override
    public int getRowCount() {
        return documents.size();
    }

    @Override
    public int getColumnCount() {
        return columnes.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnes.get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return documents.get(rowIndex).get(columnes.get(columnIndex));
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        documents.get(rowIndex).put(columnes.get(columnIndex), value);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Totes les cel·les són editables
    }

    public Document getDocument(int rowIndex) {
        return documents.get(rowIndex);
    }

    public void setDocument(int rowIndex, Document document) {
        documents.set(rowIndex, document);
    }
}
