package event;

import operation.AbstractOperation;
import operation.LoginOperation;

public class LoginEvent extends AbstractEvent {

    private String username,password;
    public LoginEvent(Object obj,String username,String  password){
        super(obj);
        this.username = username;
        this.password = password;
    }

    @Override
    public AbstractOperation getOperation(){
        return new LoginOperation();
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
