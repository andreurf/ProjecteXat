package com.projecte.event;

/**
 *
 * @author Usuario
 */
public class PublicEvent {

    private static PublicEvent instance;
    private EventXat eventXat;

    public static PublicEvent getInstance() {
        if (instance == null) {
            instance = new PublicEvent();
        }
        return instance;
    }

    private PublicEvent() {

    }
    
    public void addEventXat(EventXat event){
        this.eventXat = event;
    }
 
    public EventXat getEventXat(){
        return eventXat;
    }
}
