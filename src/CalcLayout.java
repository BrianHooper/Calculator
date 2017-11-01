import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

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
     *
     * @param controller Controller for sending button input to the model
     */
    public CalcLayout(Controller controller) {
        String actionCommand;
        KeyStroke keystroke;

        InputMap inputs = mainPanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actions = mainPanel.getActionMap();


        for(Component c : mainPanel.getComponents()) {

            if(c instanceof JButton) {

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
        Border padding = BorderFactory.createEmptyBorder(0, 10, 0, 10);
        equationField.setBorder(padding);
        lastEquation.setBorder(padding);
    }

    /**
     * Allows the main JPanel to be added to a frame
     *
     * @return JPanel object with child components
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Sets the equation field
     *
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
     *
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
     *
     * @param actionCommand String action command corresponding to a button
     * @return Keystroke object corresponding to a keyboard event
     */
    private KeyStroke getNumPadStroke(String actionCommand) {
        switch(actionCommand) {
            case "0":
                return KeyStroke.getKeyStroke("NUMPAD0");
            case "1":
                return KeyStroke.getKeyStroke("NUMPAD1");
            case "2":
                return KeyStroke.getKeyStroke("NUMPAD2");
            case "3":
                return KeyStroke.getKeyStroke("NUMPAD3");
            case "4":
                return KeyStroke.getKeyStroke("NUMPAD4");
            case "5":
                return KeyStroke.getKeyStroke("NUMPAD5");
            case "6":
                return KeyStroke.getKeyStroke("NUMPAD6");
            case "7":
                return KeyStroke.getKeyStroke("NUMPAD7");
            case "8":
                return KeyStroke.getKeyStroke("NUMPAD8");
            case "9":
                return KeyStroke.getKeyStroke("NUMPAD9");
            case "+":
                return KeyStroke.getKeyStroke("ADD");
            case "-":
                return KeyStroke.getKeyStroke("SUBTRACT");
            case "/":
                return KeyStroke.getKeyStroke("DIVIDE");
            case "*":
                return KeyStroke.getKeyStroke("MULTIPLY");
            case "^":
                return KeyStroke.getKeyStroke("CIRCUMFLEX");
            case "=":
                return KeyStroke.getKeyStroke("ENTER");
            case "b":
                return KeyStroke.getKeyStroke("BACK_SPACE");
            case ".":
                return KeyStroke.getKeyStroke("DECIMAL");
            case ")":
                return KeyStroke.getKeyStroke("RIGHT_PARENTHESIS");
            case "(":
                return KeyStroke.getKeyStroke("LEFT_PARENTHESIS");
            case "C":
                return KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
            default:
                return null;
        }
    }

    /**
     * Binds action commands to standard keyboard events
     *
     * @param actionCommand String action command corresponding to a button
     * @return Keystroke object corresponding to a keyboard event
     */
    private KeyStroke getKeyStroke(String actionCommand) {
        switch(actionCommand) {
            case "0":
                return KeyStroke.getKeyStroke("0");
            case "1":
                return KeyStroke.getKeyStroke("1");
            case "2":
                return KeyStroke.getKeyStroke("2");
            case "3":
                return KeyStroke.getKeyStroke("3");
            case "4":
                return KeyStroke.getKeyStroke("4");
            case "5":
                return KeyStroke.getKeyStroke("5");
            case "6":
                return KeyStroke.getKeyStroke("6");
            case "7":
                return KeyStroke.getKeyStroke("7");
            case "8":
                return KeyStroke.getKeyStroke("8");
            case "9":
                return KeyStroke.getKeyStroke("9");
            case "+":
                return KeyStroke.getKeyStroke('=', InputEvent.SHIFT_DOWN_MASK);
            case "-":
                return KeyStroke.getKeyStroke("MINUS");
            case "/":
                return KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0);
            case "*":
                return KeyStroke.getKeyStroke('8', InputEvent.SHIFT_DOWN_MASK);
            case "^":
                return KeyStroke.getKeyStroke('^');
            case "=":
                return KeyStroke.getKeyStroke("EQUALS");
            case "b":
                return KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0);
            case ".":
                return KeyStroke.getKeyStroke("PERIOD");
            case ")":
                return KeyStroke.getKeyStroke(')');
            case "(":
                return KeyStroke.getKeyStroke('(');
            case "C":
                return KeyStroke.getKeyStroke('c');
            default:
                return null;
        }
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if(currentFont == null) return null;
        String resultName;
        if(fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if(testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(8, 8, new Insets(0, 0, 0, 0), -1, -1));
        decimal = new JButton();
        decimal.setFocusPainted(false);
        decimal.setText(".");
        mainPanel.add(decimal, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        nine = new JButton();
        nine.setFocusPainted(false);
        nine.setText("9");
        mainPanel.add(nine, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        six = new JButton();
        six.setFocusPainted(false);
        six.setText("6");
        mainPanel.add(six, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        three = new JButton();
        three.setFocusPainted(false);
        three.setText("3");
        mainPanel.add(three, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        zero = new JButton();
        zero.setFocusPainted(false);
        zero.setText("0");
        mainPanel.add(zero, new GridConstraints(6, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        seven = new JButton();
        seven.setFocusPainted(false);
        seven.setText("7");
        mainPanel.add(seven, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        eight = new JButton();
        eight.setFocusPainted(false);
        eight.setText("8");
        mainPanel.add(eight, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        four = new JButton();
        four.setFocusPainted(false);
        four.setText("4");
        mainPanel.add(four, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        five = new JButton();
        five.setFocusPainted(false);
        five.setText("5");
        mainPanel.add(five, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        one = new JButton();
        one.setDefaultCapable(true);
        one.setFocusPainted(false);
        one.setSelected(false);
        one.setText("1");
        mainPanel.add(one, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        two = new JButton();
        two.setFocusPainted(false);
        two.setText("2");
        mainPanel.add(two, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(panel1, new GridConstraints(1, 1, 2, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null));
        equationField = new JLabel();
        equationField.setAlignmentX(0.0f);
        equationField.setAlignmentY(0.0f);
        Font equationFieldFont = this.$$$getFont$$$(null, -1, 16, equationField.getFont());
        if(equationFieldFont != null) equationField.setFont(equationFieldFont);
        equationField.setHorizontalAlignment(2);
        equationField.setHorizontalTextPosition(2);
        equationField.setText("");
        panel1.add(equationField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_SOUTHEAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), null, null, 0, false));
        lastEquation = new JLabel();
        lastEquation.setText("");
        panel1.add(lastEquation, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(0, 0, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, new Dimension(25, -1), null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(7, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 25), null, null, 0, false));
        leftParen = new JButton();
        leftParen.setFocusPainted(false);
        leftParen.setText("(");
        mainPanel.add(leftParen, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        rightParen = new JButton();
        rightParen.setFocusPainted(false);
        rightParen.setHideActionText(false);
        rightParen.setText(")");
        mainPanel.add(rightParen, new GridConstraints(6, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        equals = new JButton();
        equals.setFocusPainted(false);
        equals.setText("=");
        mainPanel.add(equals, new GridConstraints(3, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 50), new Dimension(-1, 50), new Dimension(-1, 50), 0, false));
        backspace = new JButton();
        backspace.setActionCommand("b");
        backspace.setFocusPainted(false);
        backspace.setText("←");
        mainPanel.add(backspace, new GridConstraints(5, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        Prev = new JButton();
        Prev.setFocusPainted(false);
        Prev.setText("H");
        Prev.setToolTipText("History");
        Prev.setVerifyInputWhenFocusTarget(true);
        mainPanel.add(Prev, new GridConstraints(6, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(0, 7, 8, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(25, -1), null, null, 0, false));
        divide = new JButton();
        divide.setActionCommand("/");
        divide.setFocusPainted(false);
        divide.setText("÷");
        mainPanel.add(divide, new GridConstraints(5, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), null, null, 0, false));
        multiply = new JButton();
        multiply.setFocusPainted(false);
        multiply.setText("*");
        mainPanel.add(multiply, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        clear = new JButton();
        clear.setFocusPainted(false);
        clear.setText("C");
        mainPanel.add(clear, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        exponent = new JButton();
        exponent.setFocusPainted(false);
        exponent.setText("^");
        mainPanel.add(exponent, new GridConstraints(4, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        minus = new JButton();
        minus.setFocusPainted(false);
        minus.setText("-");
        mainPanel.add(minus, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
        plus = new JButton();
        plus.setFocusPainted(false);
        plus.setText("+");
        mainPanel.add(plus, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 50), new Dimension(50, 50), new Dimension(50, 50), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
