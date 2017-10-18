package operationObject;

public class Homework {
    private int homeworkid;
    private String username;
    private String content;
    private String evaluation;
    private int grade;

    public Homework(int id, String username, String content, String evaluation, int grade) {
        this.homeworkid = id;
        this.username = username;
        this.content = content;
        this.evaluation = evaluation;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return this.username;
    }

    public int getId() {
        return homeworkid;
    }

    public void setId(int id) {
        this.homeworkid = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
