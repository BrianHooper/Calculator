import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyAction extends AbstractAction {

    private Controller control;
    private String event;

    public KeyAction(String e, Controller c) {
        this.control = c;
        this.event = e;
    }

    /**
     * Listener for keyboard events, routes input to the controller
     * @param e ActionEvent corresponding to a key press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        control.performAction(event);
    }
}