import java.util.Stack;

public class Equation {
    private Stack<Character> pfStack;
    private StringBuilder expression;
    private String errorMsg, result;
    private int size;

    public Equation() {
        pfStack = new Stack<Character>();
        clear();
    }

    /**
     * Sets the current expression to blank
     * and removes any error messages
     */
    public void clear() {
        expression = new StringBuilder();
        errorMsg = "";
        size = 0;
    }

    /**
     * Appends an element to the end of the expression
     * @param operand String element to add to the expression
     * @return true if the expression was added
     */
    public boolean addOperand(String operand) {
        expression.append(operand);
        size += operand.length();
        return true;
    }

    /**
     * Removes the last character from the expression
     * @return true if the element was removed
     */
    public boolean backspace() {
        if(size < 1) {
            return false;
        } else {
            expression.deleteCharAt(size - 1);
            size--;
            return true;
        }
    }

    /**
     * Calculates the expression. Sets errormessage
     * if the calculation failed.
     * @return true if the expression calculated successfully.
     */
    public boolean calculate() {
        String postfix = toPostFix(expression.toString());
        result = expression.toString();
        errorMsg = "";
        return true;
    }

    /**
     * Returns the current error message.
     * Blank if no error.
     * @return String error message
     */
    public String getErrorMsg() {
        if(errorMsg == null) {
            return "";
        } else {
            return "";
        }
    }

    /**
     * Returns the result of the last calculated
     * expression. Blank if there was no recent
     * calculation, or if there was an error.
     * @return String result
     */
    public String getResult() {
        return result;
    }

    /**
     * Converts the expression to postfix form for evaluation.
     *
     * @param infix expression in infix form
     * @return expression in postfix form
     */
    private String toPostFix(String infix) {
        StringBuilder postfix = new StringBuilder();



        return postfix.toString();
    }
}
