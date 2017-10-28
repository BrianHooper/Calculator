import javax.swing.*;
import java.awt.event.ActionListener;

class CalcLayout {

    private JLabel equationField;
    private JPanel mainPanel;

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
    private JLabel lastEquation;

    /**
     * Creates a new CalcLayout and assigns
     * ActionListeners to each child component
     * @param controller ActionListener for sending button input to the model
     */
    public CalcLayout(ActionListener controller) {

        one.addActionListener(controller);
        two.addActionListener(controller);
        three.addActionListener(controller);
        equals.addActionListener(controller);
        clear.addActionListener(controller);
        backspace.addActionListener(controller);
        exponent.addActionListener(controller);
        six.addActionListener(controller);
        five.addActionListener(controller);
        four.addActionListener(controller);
        seven.addActionListener(controller);
        eight.addActionListener(controller);
        nine.addActionListener(controller);
        plus.addActionListener(controller);
        minus.addActionListener(controller);
        zero.addActionListener(controller);
        decimal.addActionListener(controller);
        multiply.addActionListener(controller);
        divide.addActionListener(controller);
        leftParen.addActionListener(controller);
        rightParen.addActionListener(controller);
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

        equationField.setText("  " + message);
    }

    /**
     * Sets the history field to the current value
     * of the equation field
     */
    public void updateHistory() {
        lastEquation.setText(equationField.getText());
    }
}
