package listener;

import db.DBUtils;
import event.AbstractEvent;
import event.LoginEvent;
import gui.WindowBox;
import operationObject.LoginStatus;
import session.Session;

import javax.swing.*;

public class LoginListener implements AbstractListener {
    @Override
    public void eventOccured(AbstractEvent event) {
        if (event instanceof LoginEvent){
            String username = ((LoginEvent)event).getUsername();
            String password = ((LoginEvent)event).getPassword();
            LoginStatus loginstatus = DBUtils.login(username,password);
            if (loginstatus.isLogin()){
                Session.getInstance().setUsername(username);
                Session.getInstance().setRoleName(loginstatus.getRole());
                Session.getInstance().setUserid(loginstatus.getUserid());
                WindowBox.getInstance().RoleChange();
                JOptionPane.showMessageDialog(null,"登录成功");

            }else{
                JOptionPane.showMessageDialog(null,"登录失败");
            }
        }
    }
}
