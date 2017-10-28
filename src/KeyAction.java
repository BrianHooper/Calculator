import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyAction extends AbstractAction {

    private Controller control;
    private String event;

    public KeyAction(String e, Controller c) {
        this.control = c;
        this.event = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        control.performAction(event);
    }
}