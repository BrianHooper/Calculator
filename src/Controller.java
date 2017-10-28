import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Equation equationModel;
    private View view;
    private String inputs;

    public Controller(Equation eq, View v) {
        this.equationModel = eq;
        this.view = v;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch(action) {
            case "=": {
                view.updateHistory();
                if(equationModel.calculate()) {
                    view.update(equationModel.getResult());

                } else {
                    view.update(equationModel.getErrorMsg());
                }
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
