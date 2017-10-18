package listener;

import java.util.EventListener;
import event.AbstractEvent;

public interface AbstractListener extends EventListener {
    void eventOccured(AbstractEvent event);
}
