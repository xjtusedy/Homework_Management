package event;

import operation.AbstractOperation;
import operation.ViewOperation;
public class GetHomeworkAssigned extends AbstractEvent {

    public GetHomeworkAssigned(Object obj){
        super(obj);
    }
    @Override
    public AbstractOperation getOperation() {
        return new ViewOperation();
    }
}
