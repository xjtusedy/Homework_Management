import event.GlobalEvent;
import gui.WindowBox;
import listener.*;

public class Main {
    public static void main(String args[]) {
        GlobalEvent.getInstance().addEventListener(new LoginListener());
        GlobalEvent.getInstance().addEventListener(new SubmitListener());
        GlobalEvent.getInstance().addEventListener(new EvaluateListener());
        GlobalEvent.getInstance().addEventListener(new StatListener());
        WindowBox windowBox = WindowBox.getInstance();
    }
}
