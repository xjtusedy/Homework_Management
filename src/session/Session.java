package session;

import db.DBUtils;
import operation.AbstractOperation;

public class Session {
    private static Session session = new Session("guest","GUEST");
    private String username;
    private String roleName;
    private int userid;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private Session(String username, String roleName){
        this.username = username;
        this.roleName = roleName;

    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Session.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public static Session getInstance(){
        return session;
    }

    public boolean checkPermission(AbstractOperation operation){
        return DBUtils.checkPermission(roleName,operation.getOperationName());
    }

}
