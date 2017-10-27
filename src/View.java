import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

public class View {
    private JPanel mainPanel;

    private JLabel label;
    private JButton[] buttons;
    private ActionListener controller;

    private final int windowW = 300;
    private final int windowH = 500;
    private final String validInputs = "1234567890+-*÷^.=c←()";

    private Equation eq;


    public View(Equation equationModel) {
        // Initialize the model
        this.eq = equationModel;
        controller = new Controller(eq, this, validInputs);

        // Initialize parameters
        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setSize(windowW, windowH);

        // Initialize the main panel
        mainPanel = new JPanel();
        mainPanel.requestFocus();

        // Initialize the individual components
        label = new JLabel("");
        mainPanel.add(label);
        buttonBuilder();

        parentFrame.add(mainPanel);
        parentFrame.setVisible(true);
    }

    /**
     * Updates the text field element with a specific message
     * @param message String containing the message
     */
    public void update(String message) {
        if(label == null) {
            return;
        } else if(message.length() > 40) {
            message = "Message overflow error";
        }

        label.setText(message);
    }

    private void buttonBuilder() {
        // Initialize the buttons
        buttons = new JButton[validInputs.length()];

        for(int i = 0; i < validInputs.length(); i++) {
            buttons[i] = new JButton(validInputs.substring(i,i+1));
            buttons[i].addActionListener(controller);
            mainPanel.add(buttons[i]);
        }
    }
}
