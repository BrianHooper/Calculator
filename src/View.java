import javax.swing.*;
import java.awt.event.ActionListener;

public class View {
    private JPanel mainPanel;

    private JLabel label;
    private JButton[] buttons;
    private ActionListener controller;

    private final int windowW = 300;
    private final int windowH = 500;

    private Equation eq;


    public View(Equation equationModel) {
        // Initialize the model
        this.eq = equationModel;
        controller = new Controller(eq, this);

        // Initialize parameters for the window frame
        JFrame parentFrame = new JFrame();
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.setSize(windowW, windowH);

        // Initialize the main components
        mainPanel = new JPanel();
        label = new JLabel("");
        mainPanel.add(label);

        // Initialize the buttons
        String buttonLabels = "1234567890+-*/^.=c←()";
        buttons = new JButton[buttonLabels.length()];

        for(int i = 0; i < buttonLabels.length(); i++) {
            buttons[i] = new JButton(buttonLabels.substring(i,i+1));
            buttons[i].addActionListener(controller);
            mainPanel.add(buttons[i]);
        }

        parentFrame.add(mainPanel);

        parentFrame.setVisible(true);
    }

    public void update(String message) {
        if(label == null) return;

        label.setText(message);
    }
}
