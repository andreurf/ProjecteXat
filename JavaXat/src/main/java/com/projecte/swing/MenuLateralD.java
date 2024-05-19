package com.projecte.swing;

import com.projecte.prova.Client;
import com.projecte.prova.Missatge;
import com.projecte.prova.MongoServeis;
import com.toedter.calendar.JCalendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andreu i quim
 */
public class MenuLateralD extends javax.swing.JPanel {

    private final ChatTitol chatTitol;
    private final ChatBody chatBody;
    private final Client client;
    private Date selectedDate;
    private final List<DateChangeListener> listeners = new ArrayList<>();

    public MenuLateralD(ChatTitol chatTitol, ChatBody chatBody, Client client) {
        initComponents();
        this.chatTitol = chatTitol;
        this.chatBody = chatBody;
        this.client = client;
        this.selectedDate = new Date();
        init();
    }

    private void init() {
        jCalendar1.addPropertyChangeListener("calendar", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                selectedDate = jCalendar1.getDate();
                notifyDateChangeListeners();
            }
        });
    }
    
    public void addDateChangeListener(DateChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyDateChangeListeners() {
        for (DateChangeListener listener : listeners) {
            listener.onDateChanged(selectedDate);
        }
    }

    public Date getSelectedDate() {
        if (selectedDate == null) {
            return new Date();
        }
        System.out.println("Data" + selectedDate);
        return selectedDate;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new java.awt.BorderLayout());
        add(jCalendar1, java.awt.BorderLayout.NORTH);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JCalendar jCalendar1;
    // End of variables declaration//GEN-END:variables
}
