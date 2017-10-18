package listener;

import db.DBUtils;
import event.AbstractEvent;
import event.SubmitEvent;
import session.Session;

import javax.swing.*;

public class SubmitListener implements AbstractListener {
    @Override
    public void eventOccured(AbstractEvent event) {
        if (event instanceof SubmitEvent){
            DBUtils.addHomework(
                    Session.getInstance().getUserid(),
                    ((SubmitEvent) event).getContent(),
                    ((SubmitEvent) event).getHomeworkAssignID()
            );
            JOptionPane.showMessageDialog(null,"提交成功");
        }
    }
}
