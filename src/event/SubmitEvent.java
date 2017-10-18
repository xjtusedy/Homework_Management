package event;

import operation.AbstractOperation;
import operation.SubmitOperation;

public class SubmitEvent extends AbstractEvent {
    private String content;
    private int HomeworkAssignID;
    public SubmitEvent(Object obj,String content,int HomeworkAssignID){
        super(obj);
        this.content = content;
        this.HomeworkAssignID = HomeworkAssignID;

    }

    @Override
    public AbstractOperation getOperation() {
        return new SubmitOperation();
    }

    public String getContent(){
        return content;
    }

    public int getHomeworkAssignID(){
        return HomeworkAssignID;
    }
}
