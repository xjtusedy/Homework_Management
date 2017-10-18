package gui;

import javax.swing.*;
import session.Session;
public class WindowBox {
    private static WindowBox windowBox = new WindowBox();
    private JFrame frame;

    private WindowBox() {
        frame = new JFrame();
        RoleChange();
    }

    public static WindowBox getInstance() {
        return windowBox;
    }

    public void RoleChange() {
        AbstractWindow window = WindowFactory.getInstance(Session.getInstance().getRoleName());
        frame.setTitle(window.getTitle());
        frame.setContentPane(window.getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
