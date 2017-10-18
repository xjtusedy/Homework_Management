package listener;

import db.DBUtils;
import event.AbstractEvent;
import event.StatEvent;
import javax.swing.*;

public class StatListener implements AbstractListener {
    @Override
    public void eventOccured(AbstractEvent event) {
        if (event instanceof StatEvent){
            String msg = DBUtils.getStat(((StatEvent) event).getHomeworkAssigned().getHomeworkAssignID()).toString();
            JOptionPane.showMessageDialog(null, msg);
        }
    }
}
