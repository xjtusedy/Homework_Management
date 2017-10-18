package operationObject;

public class LoginStatus {
    private int userid;
    private String role;
    private boolean status;

    public LoginStatus(boolean status) {
        this.status = status;
    }

    public LoginStatus(String role, int userid) {
        this.status = true;
        this.role = role;
        this.userid = userid;
    }

    public boolean isLogin() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public int getUserid() {
        return userid;
    }
}
