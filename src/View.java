import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class View {

    // Maximum number of characters in the equation field
    public static final int MAX_SIZE = 25;

    private CalcLayout layout;

    /**
     * Instantiates the GUI
     * @param equationModel Model field for calculating expressions
     */
    public View(Equation equationModel) {
        // Initialize the model
        Controller controller = new Controller(equationModel, this);
        layout = new CalcLayout(controller);

        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(layout.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Updates the text field element with a specific message
     * @param message String containing the message
     */
    public void update(String message) {
        layout.update(message);
    }

    /**
     * Sets the history field to the value of the equation field,
     * so that when an expression is calculated, both the result
     * and the expression are shown
     */
    public void updateHistory() {
        layout.updateHistory();
    }

    /**
     * Getter method for last equation text field
     * @return Current value of last equation text field
     */
    public String getHistory() {
        return layout.getHistory();
    }

    /**
     * Clears the last equation text field
     */
    public void clearHistory() {
        layout.clearHistory();
    }
}
