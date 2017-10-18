package operationObject;

public class HomeworkAssigned {
    private int HomeworkAssignID;
    private String title;
    public HomeworkAssigned(int HomeworkAssigID,String title){
        this.HomeworkAssignID = HomeworkAssigID;
        this.title = title;
    }

    public int getHomeworkAssignID() {
        return HomeworkAssignID;
    }

    public void setHomeworkAssignID(int homeworkAssignID) {
        HomeworkAssignID = homeworkAssignID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}
