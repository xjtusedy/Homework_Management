package event;

import listener.AbstractListener;
import session.Session;

import java.util.Collection;
import java.util.HashSet;

public class GlobalEvent {

    private Collection<AbstractListener> listeners;
    private static GlobalEvent globalevent = new GlobalEvent();

    private GlobalEvent(){
        listeners = new HashSet<>();
    }

    public void addEventListener(AbstractListener listener){
        listeners.add(listener);
    }

    public void removeEventListener(AbstractListener listener){
        listeners.remove(listener);
    }

    public void occureEvent(AbstractEvent event){
        if(!Session.getInstance().checkPermission(event.getOperation())){
            return;
        }
        for(AbstractListener listener : listeners){
            listener.eventOccured(event);
        }
    }
    public static  GlobalEvent getInstance(){
        return globalevent;
    }

}
