package event;

import operation.AbstractOperation;
import operation.SubmitOperation;
import operationObject.HomeworkAssigned;

public class GetselfHomework extends AbstractEvent {
    private HomeworkAssigned homeworkAssigned;

    public GetselfHomework(Object obj,HomeworkAssigned homeworkAssigned){
        super(obj);
        this.homeworkAssigned = homeworkAssigned;
    }
    @Override
    public AbstractOperation getOperation() {
        return new SubmitOperation();
    }

    public HomeworkAssigned getHomeworkAssigned() {
        return homeworkAssigned;
    }
}
