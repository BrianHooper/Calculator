import javax.swing.*;
import java.awt.event.ActionEvent;

class KeyAction extends AbstractAction {

    private final Controller control;
    private final String event;

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