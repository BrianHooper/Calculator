import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

class Equation {
    private StringBuilder expression;
    private String errorMsg;
    private double result;
    private int size;

    /**
     * Creates and initializes a new Equation model
     */
    public Equation() {
        clear();
    }

    /**
     * Sets the current expression to blank
     * and removes any error messages
     */
    public void clear() {
        expression = new StringBuilder();
        result = 0.0;
        errorMsg = "";
        size = 0;
    }

    /**
     * Getter method for the expression
     * @return String representing the current expression
     */
    public String getExpression() {
        return expression.toString();
    }

    /**
     * Setter method for the expression
     * @param expr String parameter for setting expression
     */
    public void setExpression(String expr) {
        clear();
        expression.append(expr);
        size = expr.length();
    }

    /**
     * Sets the current error message
     * @param msg String value with the current error message
     */
    private void setErrorMessage(String msg) {
        errorMsg = msg;
    }

    /**
     * Appends an element to the end of the expression
     *
     * @param operand String element to add to the expression
     * @return true if the expression was added
     */
    public boolean addOperand(String operand) {
        if (expression.length() + operand.length() > View.MAX_SIZE) {
            return false;
        } else {
            expression.append(operand);
            size += operand.length();
            return true;
        }
    }

    /**
     * Removes the last character from the expression
     *
     * @return true if the element was removed
     */
    public boolean backspace() {
        if (size < 1) {
            return false;
        } else {
            expression.deleteCharAt(size - 1);
            size--;
            return true;
        }
    }

    /**
     * Calculates the expression. Sets error message
     * if the calculation failed.
     *
     * @return true if the expression calculated successfully.
     */
    public boolean calculate() {
        // Don't try to evaluate a blank expression
        if (expression.length() == 0) {
            return false;
        }

        LinkedList<String> exprList;

        try {
            exprList = ExpressionConverter.toLinkedList(expression.toString());
            exprList = ExpressionConverter.toPostFix(exprList);
            return evaluate(exprList);
        } catch (NumberFormatException | InvalidExpressionException e) {
            setErrorMessage("Improper expression error");
            return false;
        }
    }

    /**
     * Returns the current error message.
     * Blank if no error.
     *
     * @return String error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Returns the result of the last calculated
     * expression.
     *
     * @return String result
     */
    public String getResult() {
        if (result - ((int) result) == 0) {
            return "= " + String.valueOf((int) result);
        } else {
            return "= " + String.valueOf(result);
        }
    }

    /**
     * Attempts to evaluate a postfix expression
     *
     * @param postfix LinkedList of Strings representing an expression in
     *                postfix form
     * @return true if the expression was successfully evaluated
     */
    private boolean evaluate(LinkedList<String> postfix) {
        Stack<String> stack = new Stack<>();
        String current, a, b;

        while (!postfix.isEmpty()) {
            current = postfix.removeFirst();

            if (ExpressionConverter.isNumber(current)) {
                stack.push(current);
            } else {
                try {
                    b = stack.pop();
                    if (current.equals("_")) {
                        stack.push("-" + b);
                    } else {
                        a = stack.pop();
                        current = performCalculation(a, b, current);
                        stack.push(current);
                    }
                } catch (NumberFormatException | EmptyStackException |
                        NoSuchElementException e) {
                    setErrorMessage("Improper expression error");
                    return false;
                } catch (InvalidExpressionException e) {
                    setErrorMessage(e.getMessage());
                    return false;
                } catch (ArithmeticException e) {
                    setErrorMessage("Arithmetic overflow error");
                    return false;
                }
            }
        }

        try {
            result = Double.parseDouble(stack.pop());
            return true;
        } catch (NumberFormatException | EmptyStackException e) {
            setErrorMessage("Improper expression error");
            return false;
        }
    }

    /**
     * Attempts to perform a calculation between two numbers
     *
     * @param a        String representation of the first number
     * @param b        String representation of the second number
     * @param operator String representing the mathematical operator
     * @return String containing the result of the expression
     * @throws InvalidExpressionException Thrown when the expression is invalid,
     *                                    such as division by zero or an unknown operator
     * @throws NumberFormatException      thrown when either a or b cannot be
     *                                    converted to a floating-point number
     */
    private String performCalculation(String a, String b, String operator)
            throws InvalidExpressionException, NumberFormatException {
        double operationResult;
        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);

        switch (operator) {
            case "^":
                operationResult = Math.pow(num1, num2);
                if (Double.isInfinite(operationResult)) {
                    throw new InvalidExpressionException(
                            "Arithmetic overflow error");
                }
                break;
            case "*":
                operationResult = num1 * num2;
                break;
            case "/":
                if (num2 == 0.0) {
                    throw new InvalidExpressionException(
                            "Division by zero error");
                }
                operationResult = num1 / num2;
                break;
            case "+":
                operationResult = num1 + num2;
                break;
            case "-":
                operationResult = num1 - num2;
                break;
            default:
                throw new InvalidExpressionException("Unknown operator error");
        }

        return String.valueOf(operationResult);
    }
}