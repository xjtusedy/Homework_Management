package gui;

public class WindowFactory {
    static AbstractWindow getInstance(String roleName) {
        switch (roleName) {
            case "GUEST":
                return new LoginWindow();
            case "STUDENT":
            case "TEACHER":
            case "DEAN":
                return new IndexWindow();
            default:
                throw new Error("Invalid role");
        }
    }
}
