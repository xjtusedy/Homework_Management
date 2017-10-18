package event;

import operation.AbstractOperation;
import operation.StatOperation;
import operationObject.HomeworkAssigned;

public class StatEvent extends AbstractEvent {
    private HomeworkAssigned homeworkAssigned;
    public StatEvent(Object obj,HomeworkAssigned homeworkAssigned){
        super(obj);
        this.homeworkAssigned = homeworkAssigned;
    }

    @Override
    public AbstractOperation getOperation() {
        return new StatOperation();
    }

    public HomeworkAssigned getHomeworkAssigned() {
        return homeworkAssigned;
    }
}
