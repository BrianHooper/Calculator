import javax.swing.*;
import java.awt.event.ActionListener;

public class View {
    private Equation eq;
    private CalcLayout layout;

    public View(Equation equationModel) {
        eq = equationModel;
        // Initialize the model
        ActionListener controller = new Controller(eq, this);
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

    public void updateHistory() {
        layout.updateHistory();
    }
}
