package operationObject;

import event.StatEvent;

public class StatGrade {
    private int submitCount;
    private int evalCount;
    private double avgGrade;

    public StatGrade(int submitCount,int evalCount,double avgGrade){
        this.submitCount = submitCount;
        this.evalCount = evalCount;
        this.avgGrade = avgGrade;
    }

    @Override
    public String toString() {
        return
                "提交人数：" + Integer.toString(submitCount) + "\n" +
                        "批改人数：" + Integer.toString(evalCount) + "\n" +
                        "平均分：" + Double.toString(avgGrade);
    }
}
