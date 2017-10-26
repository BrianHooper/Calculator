import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private Equation equationModel;
    private View view;

    public Controller(Equation eq, View v) {
        this.equationModel = eq;
        this.view = v;
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
