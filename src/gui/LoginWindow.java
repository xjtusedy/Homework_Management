package gui;

import event.GlobalEvent;
import event.LoginEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* 实现登录界面
* */
public class LoginWindow implements AbstractWindow{

    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPanel mainPanel;
    private JButton LoginButton;

    public LoginWindow(){
        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GlobalEvent.getInstance().occureEvent(new LoginEvent(
                        LoginWindow.this,
                        usernameTextField.getText(),
                        passwordField.getText()
                ));
            }
        });
    }

    @Override
    public JPanel getPanel(){
        return mainPanel;
    }

    @Override
    public String getTitle(){
        return "login";
    }

}
