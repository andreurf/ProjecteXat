package com.projecte.swing;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralD extends javax.swing.JPanel {
    
    private Date dataSeleccionada;
    private final List<CanviDataListener> listeners = new ArrayList<>();

    public MenuLateralD() {
        initComponents();
        this.dataSeleccionada = new Date();
        init();
    }

    private void init() {
        jCalendar1.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                dataSeleccionada = jCalendar1.getDate();
                canviData();
            }
        });
    }
    
    public void addDateChangeListener(CanviDataListener listener) {
        listeners.add(listener);
    }

    private void canviData() {
        for (CanviDataListener listener : listeners) {
            listener.canviData(dataSeleccionada);
        }
    }

    public Date getDataSeleccionada() {
        if (dataSeleccionada == null) {
            return new Date();
        }
        return dataSeleccionada;
    }
    
    public void changeTheme(boolean isDarkTheme){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new java.awt.BorderLayout());
        add(jCalendar1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar1;
    // End of variables declaration//GEN-END:variables
}
