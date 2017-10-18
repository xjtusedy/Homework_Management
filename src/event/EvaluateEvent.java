package event;
import operation.AbstractOperation;
import operation.EvaluateOperation;
import operationObject.Homework;
public class EvaluateEvent extends AbstractEvent {
    private Homework homework;
    private String evaluation;
    private int grade;

    public EvaluateEvent(Object obj,Homework homework,String evaluation,int grade){
        super(obj);
        this.homework = homework;
        this.evaluation = evaluation;
        this.grade = grade;
    }


    @Override
    public AbstractOperation getOperation() {
        return new EvaluateOperation();
    }

    public Homework getHomework() {
        return homework;
    }

    public int getGrade() {
        return grade;
    }

    public String getEvaluation() {
        return evaluation;
    }
}
