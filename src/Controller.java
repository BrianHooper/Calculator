import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Controller extends AbstractAction {
    private Equation equationModel;
    private View view;

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

    public void performAction(String action) {
        switch(action) {
            case "=": {
                view.updateHistory();
                if(equationModel.calculate()) {
                    view.update(equationModel.getResult());

                } else {
                    view.update(equationModel.getErrorMsg());
                }
                System.out.println(equationModel.getErrorLog());
                equationModel.clear();
                break;
            }
            case "C":
                equationModel.clear();
                view.update("");
                view.updateHistory();
                break;
            case "b":
                if(equationModel.backspace()) {
                    view.update(equationModel.getExpression());
                }
                break;
            default:
                if(equationModel.addOperand(action)) {
                    view.update(equationModel.getExpression());
                }
                break;
        }
    }
}
