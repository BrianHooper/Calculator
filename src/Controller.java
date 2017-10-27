import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Equation equationModel;
    private View view;
    private String inputs;

    public Controller(Equation eq, View v, String validInputs) {
        this.equationModel = eq;
        this.view = v;
        this.inputs = validInputs;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch(action) {
            case "=": {
                if(equationModel.calculate()) {
                    view.update(equationModel.getResult());

                } else {
                    view.update(equationModel.getErrorMsg());
                }
                equationModel.clear();
                break;
            }
            case "c":
                equationModel.clear();
                view.update("");
                break;
            case "←":
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
