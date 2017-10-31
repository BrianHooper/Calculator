import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class CalcLayout {

    private JLabel equationField;
    private JPanel mainPanel;
    private JLabel lastEquation;

    private JButton one;
    private JButton two;
    private JButton three;
    private JButton equals;
    private JButton clear;
    private JButton backspace;
    private JButton exponent;
    private JButton six;
    private JButton five;
    private JButton four;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton plus;
    private JButton minus;
    private JButton zero;
    private JButton decimal;
    private JButton multiply;
    private JButton divide;
    private JButton leftParen;
    private JButton rightParen;
    private JButton Prev;


    /**
     * Creates a new CalcLayout and assigns
     * ActionListeners to each child component
     * @param controller Controller for sending button input to the model
     */
    public CalcLayout(Controller controller) {
        String actionCommand;
        KeyStroke keystroke;

        InputMap inputs = mainPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = mainPanel.getActionMap();


        for(Component c : mainPanel.getComponents()) {

            if(c instanceof  JButton) {

                actionCommand = ((JButton) c).getActionCommand();

                // Map keyboard bindings to specific button commands
                keystroke = getKeyStroke(actionCommand);
                inputs.put(keystroke, actionCommand);
                keystroke = getNumPadStroke(actionCommand);
                inputs.put(keystroke, actionCommand);
                actions.put(actionCommand, new KeyAction(actionCommand, controller));

                // Map the controller to the button
                ((JButton) c).addActionListener(controller);
            }
        }

        // 10px internal padding within the equation field
        Border padding = BorderFactory.createEmptyBorder(0,10,0,10);
        equationField.setBorder(padding);
        lastEquation.setBorder(padding);
    }

    /**
     * Allows the main JPanel to be added to a frame
     * @return JPanel object with child components
     */
    public  JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Sets the equation field
     * @param message String that the field will display
     */
    public void update(String message) {

        equationField.setText(message);
    }

    /**
     * Sets the history field to the current value
     * of the equation field
     */
    public void updateHistory() {
        lastEquation.setText(equationField.getText());
    }

    /**
     * Getter method for last equation text field
     * @return Current value of last equation text field
     */
    public String getHistory() {
        return lastEquation.getText();
    }

    /**
     * Clear the last equation text field
     */
    public void clearHistory() {
        lastEquation.setText("");
    }

    /**
     * Binds action commands to numberpad keyboard events
     * @param actionCommand String action command corresponding to a button
     * @return Keystroke object corresponding to a keyboard event
     */
    private KeyStroke getNumPadStroke(String actionCommand) {
        switch (actionCommand) {
            case "0": return KeyStroke.getKeyStroke("NUMPAD0");
            case "1": return KeyStroke.getKeyStroke("NUMPAD1");
            case "2": return KeyStroke.getKeyStroke("NUMPAD2");
            case "3": return KeyStroke.getKeyStroke("NUMPAD3");
            case "4": return KeyStroke.getKeyStroke("NUMPAD4");
            case "5": return KeyStroke.getKeyStroke("NUMPAD5");
            case "6": return KeyStroke.getKeyStroke("NUMPAD6");
            case "7": return KeyStroke.getKeyStroke("NUMPAD7");
            case "8": return KeyStroke.getKeyStroke("NUMPAD8");
            case "9": return KeyStroke.getKeyStroke("NUMPAD9");
            case "+": return KeyStroke.getKeyStroke("ADD");
            case "-": return KeyStroke.getKeyStroke("SUBTRACT");
            case "/": return KeyStroke.getKeyStroke("DIVIDE");
            case "*": return KeyStroke.getKeyStroke("MULTIPLY");
            case "^": return KeyStroke.getKeyStroke("CIRCUMFLEX");
            case "=": return KeyStroke.getKeyStroke("ENTER");
            case "b": return KeyStroke.getKeyStroke("BACK_SPACE");
            case ".": return KeyStroke.getKeyStroke("DECIMAL");
            case ")": return KeyStroke.getKeyStroke("RIGHT_PARENTHESIS");
            case "(": return KeyStroke.getKeyStroke("LEFT_PARENTHESIS");
            case "C": return KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
            default: return null;
        }
    }

    /**
     * Binds action commands to standard keyboard events
     * @param actionCommand String action command corresponding to a button
     * @return Keystroke object corresponding to a keyboard event
     */
    private KeyStroke getKeyStroke(String actionCommand) {
        switch (actionCommand) {
            case "0": return KeyStroke.getKeyStroke("0");
            case "1": return KeyStroke.getKeyStroke("1");
            case "2": return KeyStroke.getKeyStroke("2");
            case "3": return KeyStroke.getKeyStroke("3");
            case "4": return KeyStroke.getKeyStroke("4");
            case "5": return KeyStroke.getKeyStroke("5");
            case "6": return KeyStroke.getKeyStroke("6");
            case "7": return KeyStroke.getKeyStroke("7");
            case "8": return KeyStroke.getKeyStroke("8");
            case "9": return KeyStroke.getKeyStroke("9");
            case "+": return KeyStroke.getKeyStroke('=', InputEvent.SHIFT_DOWN_MASK);
            case "-": return KeyStroke.getKeyStroke("MINUS");
            case "/": return KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0);
            case "*": return KeyStroke.getKeyStroke('8', InputEvent.SHIFT_DOWN_MASK);
            case "^": return KeyStroke.getKeyStroke('^');
            case "=": return KeyStroke.getKeyStroke("EQUALS");
            case "b":  return KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0);
            case ".": return KeyStroke.getKeyStroke("PERIOD");
            case ")": return KeyStroke.getKeyStroke(')');
            case "(": return KeyStroke.getKeyStroke('(');
            case "C": return KeyStroke.getKeyStroke('c');
            default: return null;
        }
    }
}
