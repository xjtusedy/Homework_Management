package gui;

import db.DBUtils;
import event.*;
import operation.*;
import operationObject.*;
import operationObject.HomeworkAssigned;
import session.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Consumer;
import java.util.Arrays;

public class IndexWindow implements AbstractWindow {

    private JPanel mainPanel;
    private JTextArea DescriptionTextArea;
    private JTextField EvaluateTextField;
    private JTextField GradeTextField;
    private JButton SubmitButton;
    private JButton EvaluateButton;
    private JButton StatButton;
    private JComboBox HomeworkAssignSelect;
    private JComboBox HomeworkSelect;
    private JTextArea ContentTextArea;

    public IndexWindow(){
        updateUIByPerm();
        GlobalEvent.getInstance().addEventListener((AbstractEvent event) -> {
            if (event instanceof GetHomeworkAssigned) {
                HomeworkAssignSelect.setModel(new DefaultComboBoxModel<>(DBUtils.getHomeworkAssigNames()));
                updateHomeworkDescription();
            }
        });
        GlobalEvent.getInstance().addEventListener((AbstractEvent event) -> {
            if (event instanceof GetHomework) {
                HomeworkSelect.setModel(
                        new DefaultComboBoxModel<>(DBUtils.getHomeworkNames(
                                ((GetHomework) event).getHomeworkAssigned().getHomeworkAssignID()
                        ))
                );
            } else if (event instanceof GetselfHomework) {
                HomeworkSelect.setModel(
                        new DefaultComboBoxModel<>(DBUtils.getSelfHomeworkNames(
                                Session.getInstance().getUserid(),
                                ((GetselfHomework) event).getHomeworkAssigned().getHomeworkAssignID()
                        ))
                );
            }
        });
        HomeworkAssignSelect.addActionListener((ActionEvent e) ->
                updateHomeworkDescription()
        );
        HomeworkSelect.addActionListener((ActionEvent e) ->
                updateHomeworkContent()
        );
        SubmitButton.addActionListener((ActionEvent e) -> {
            GlobalEvent.getInstance().occureEvent(new SubmitEvent(
                    this,
                    ContentTextArea.getText(),
                    ((HomeworkAssigned) HomeworkAssignSelect.getSelectedItem()).getHomeworkAssignID()
            ));
        });
        EvaluateButton.addActionListener((ActionEvent e) -> {
            GlobalEvent.getInstance().occureEvent(new EvaluateEvent(
                    this,
                    (Homework) HomeworkSelect.getSelectedItem(),
                    EvaluateTextField.getText(),
                    Integer.valueOf(GradeTextField.getText())
            ));
        });
        StatButton.addActionListener((ActionEvent e) -> {
            GlobalEvent.getInstance().occureEvent(new StatEvent(this, (HomeworkAssigned) HomeworkAssignSelect.getSelectedItem()));
        });
        GlobalEvent.getInstance().occureEvent(new GetHomeworkAssigned(this));

    }


    private List<Object[]> permPairs = Arrays.asList(
            new Object[][]{
                    {(Consumer<Boolean>) ((v) -> SubmitButton.setEnabled(v)), new SubmitOperation()},
                    {(Consumer<Boolean>) ((v) -> ContentTextArea.setEditable(v)), new SubmitOperation()},
                    {(Consumer<Boolean>) ((v) -> EvaluateButton.setEnabled(v)), new EvaluateOperation()},
                    {(Consumer<Boolean>) ((v) -> EvaluateTextField.setEditable(v)), new EvaluateOperation()},
                    {(Consumer<Boolean>) ((v) -> GradeTextField.setEditable(v)), new EvaluateOperation()},
                    {(Consumer<Boolean>) ((v) -> StatButton.setEnabled(v)), new StatOperation()},
                    {(Consumer<Boolean>) ((v) -> HomeworkAssignSelect.setEnabled(v)), new ViewOperation()},
                    {(Consumer<Boolean>) ((v) -> HomeworkSelect.setEnabled(v)), new StatOperation()},
            }
    );
    private void updateUIByPerm() {
        for (Object[] permPair : permPairs) {
            ((Consumer<Boolean>) permPair[0]).accept((Session.getInstance().checkPermission((AbstractOperation) permPair[1])));
        }
    }

    private void updateHomeworkContent() {
        Homework homework = (Homework) HomeworkSelect.getSelectedItem();
        if (homework == null) return;
        ContentTextArea.setText(homework.getContent());
        EvaluateTextField.setText(homework.getEvaluation());
        GradeTextField.setText(Integer.toString(homework.getGrade()));
    }

    private void updateHomeworkDescription() {
        HomeworkAssigned homeworkAssigned = (HomeworkAssigned) HomeworkAssignSelect.getSelectedItem();
        if (homeworkAssigned == null) return;
        DescriptionTextArea.setText(DBUtils.getDescription(homeworkAssigned.getHomeworkAssignID()));
        GlobalEvent.getInstance().occureEvent(new GetHomework(this, homeworkAssigned));
        GlobalEvent.getInstance().occureEvent(new GetselfHomework(this, homeworkAssigned));
        updateHomeworkContent();
    }


    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public String getTitle() {
        return "Index";
    }

}