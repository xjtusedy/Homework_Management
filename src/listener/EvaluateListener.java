package listener;

import db.DBUtils;
import event.AbstractEvent;
import event.EvaluateEvent;

import javax.swing.*;

public class EvaluateListener implements AbstractListener {
    @Override
    public void eventOccured(AbstractEvent event) {
        if (event instanceof EvaluateEvent){
            DBUtils.evaluateHomework(
                    ((EvaluateEvent) event).getHomework().getId(),
                    ((EvaluateEvent) event).getEvaluation(),
                    ((EvaluateEvent) event).getGrade()

            );
            JOptionPane.showMessageDialog(null,"评价成功");
        }
    }
}
