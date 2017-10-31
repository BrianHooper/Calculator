import javax.swing.*;
import java.awt.event.ActionEvent;

class Controller extends AbstractAction {
    private final Equation equationModel;
    private final View view;

    /**
     * Creates a new ActionListener object for
     * sending button input to the model
     * @param eq The Equation object that the input is sent to
     * @param v The view that this ActionListener is receiving events from
     */
    public Controller(Equation eq, View v) {
        this.equationModel = eq;
        this.view = v;
    }

    /**
     * Sends action events to the model
     * @param actionEvent actionEvent object representing a button press
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        performAction(action);
    }

    /**
     * ActionListener for button presses or keyboard events
     * Routes input from the GUI to the equation model
     * @param action Action command
     */
    public void performAction(String action) {
        switch(action) {
            case "=": compute(); break;
            case "C": clear(); break;
            case "b": backspace(); break;
            case "H": history(); break;
            default:
                if(equationModel.addOperand(action)) {
                    view.update(equationModel.getExpression());
                }
                break;
        }
    }

    /**
     * Attempt to perform the calculation
     * currently in the equation field
     */
    private void compute() {

        // Set the history field equal to the current equation field
        view.updateHistory();

        // Attempt to perform the calculation
        // If valid, set the equation field to the result, otherwise
        // set it to the error message
        if(equationModel.calculate()) {
            view.update(equationModel.getResult());

        } else {
            view.update(equationModel.getErrorMsg());
        }

        // Reset the equation for next input
        equationModel.clear();
    }

    /**
     * Clear both the equation and history fields
     */
    private void clear() {
        equationModel.clear();
        view.update("");
        view.updateHistory();
    }

    /**
     * Remove the last char from the equation field
     */
    private void backspace() {
        if(equationModel.backspace()) {
            view.update(equationModel.getExpression());
        }
    }

    /**
     * Place the last calculated equation back into the equation field
     */
    private void history() {
        String history = view.getHistory();
        if(history.length() > 0) {
            view.update(history);
            view.clearHistory();
            equationModel.setExpression(history);
        }

    }

}
