package com.projecte.event;

/**
 *
 * @author Usuario
 */
public class PublicEvent {

    private static PublicEvent instance;
    private EventImatgeVista eventImatgeVista;
    private EventXat eventXat;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }

    public void addEventImatgeVista(EventImatgeVista event) {
        this.eventImatgeVista = event;
    }
    
    public void addEventXat(EventXat event){
        this.eventXat = event;
    }

    public EventImatgeVista getEventImatgeVista() {
        return eventImatgeVista;
    }
    
    public EventXat getEventXat(){
        return eventXat;
    }
}
