import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Equation {
    private StringBuilder expression;
    private String errorMsg;
    private double result;
    private int size;
    private final int maxLength = 25;

    public Equation() {
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
     *
     * @param operand String element to add to the expression
     * @return true if the expression was added
     */
    public boolean addOperand(String operand) {
        if(expression.length() + operand.length() > maxLength) {
            errorMsg = "Maximum length reached";
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
        if(size < 1) {
            return false;
        } else {
            expression.deleteCharAt(size - 1);
            size--;
            return true;
        }
    }

    public String getExpression() {
        return expression.toString();
    }

    /**
     * Calculates the expression. Sets error message
     * if the calculation failed.
     *
     * @return true if the expression calculated successfully.
     */
    public boolean calculate() {
        if(expression.length() == 0) {
            return false;
        }

        LinkedList<String> exprList;

        try {
            exprList = toLinkedList(expression.toString());
            exprList = toPostFix(exprList);
            return evaluate(exprList);
        } catch(NumberFormatException|InvalidExpressionException e) {
            errorMsg = "Invalid expression";
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
     * expression. Blank if there was no recent
     * calculation, or if there was an error.
     *
     * @return String result
     */
    public String getResult() {
        if(result - ((int) result) == 0) {
            return String.valueOf((int) result);
        } else {
            return String.valueOf(result);
        }
    }

    /**
     * Converts an expression in infix form to a LinkedList of Strings
     * where each element is either a number or an operator
     * @param infix String element representing an infix expression
     * @return LinkedList of Strings
     * @throws NumberFormatException throws exception if a number is invalid
     */
    private LinkedList<String> toLinkedList(String infix) throws
            NumberFormatException{
        StringBuilder numberBuilder = new StringBuilder();
        LinkedList<String> ifList = new LinkedList<>();

        // Iterate over each character in the infix string
        for(int i = 0; i < infix.length(); i++) {
            // Retrieve a character from the infix string
            char curChar = infix.charAt(i);

            // If the character is an operator, add it to the pfList
            if(!isNumber(curChar)) {

                // If there is a number currently being built
                // Add it to the pfList and clear the builder
                if(numberBuilder.length() > 0) {
                    // Check that the number in the builder is valid
                    Double.parseDouble(numberBuilder.toString());

                    ifList.add(numberBuilder.toString());
                    numberBuilder = new StringBuilder();
                }
                // Add the operator to the pfList
                ifList.add(String.valueOf(curChar));
            } else {
                // Append the current character to the numberBuilder
                numberBuilder.append(curChar);
            }
        }

        // Make sure the numberBuilder is cleared
        if(numberBuilder.length() > 0) {

            // Check that the number in the builder is valid
            Double.parseDouble(numberBuilder.toString());
            ifList.add(numberBuilder.toString());
        }

        return ifList;
    }

    /**
     * Converts an infix LinkedList expression to a
     * postfix LinkedList expression
     * @param infix LinkedList of Strings representing the infix expression
     * @return LinkedList of Strings as a postfix expression
     */
    private LinkedList<String> toPostFix(LinkedList<String> infix) throws
            InvalidExpressionException{
        LinkedList<String> postfix = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        String currentElement = null;
        String lastElement = null;

        // Iterate over each element in the infix list
        while(infix.size() > 0) {
            // Pop the first element from the infix list
            currentElement = infix.removeFirst();

            if(currentElement.equals("(")) {
                // Push the ( to the stack
                stack.push(currentElement);
            } else if(currentElement.equals(")")) {
                // Pop the operands off the stack until a ) is encountered
                while(!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                if(!stack.isEmpty() && stack.peek().equals("(")) stack.pop();
            } else if(isNumber(currentElement)) {
                // Append the number to the postfix list
                postfix.add(currentElement);
                if(!stack.isEmpty() && stack.peek().equals("_")){
                    postfix.add(stack.pop());
                }
            } else {
                if(currentElement.equals("-")) {
                    if(lastElement == null) {
                        stack.push("_");
                    } else if(!lastElement.equals(")") && !isNumber(lastElement)) {
                        stack.push("_");
                }

                } else if(stack.isEmpty()) {
                    stack.push(currentElement);
                } else {
                    // Append the operators on the stack to the postfix list
                    // Until a lower precedence operator is encountered
                    while(!stack.isEmpty() && precedence(currentElement,
                            stack.peek())) {
                        postfix.add(stack.pop());
                    }
                    stack.push(currentElement);
                }
            }
            lastElement = currentElement;
        }

        // Add any remaining operators in the stack to the postfix list
        while(!stack.isEmpty()) {
            if(stack.peek().equals("(")) {
                throw new InvalidExpressionException();
            }
            else postfix.add(stack.pop());
        }

        return postfix;
    }

    /**
     * Checks if one input has precedence over the other
     * using ^ greater than * / greater than + -
     * @param a String operator
     * @param b String operator
     * @return true if a > b
     */
    private boolean precedence(String b, String a) {
        return value(a) >= value(b);
    }

    /**
     * Applies a value to an operator
     * @param s The operator as a String
     * @return int value representing the operators precedence
     */
    private int value(String s) {
        switch(s) {
            case "^" :
                return 5;
            case "*" :
                return 4;
            case "/" :
                return 3;
            case "+" :
                return 2;
            case "-" :
                return 1;
            default:
                return 0;
        }
    }

    /**
     * Checks that the input can be expressed as a valid
     * floating-point number
     * @param s String input
     * @return true if the input is a number
     */
    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks that the input can be expressed as a valid
     * floating-point number
     * @param c Character input
     * @return true if the input is a number
     */
    private boolean isNumber(char c) {
        return isNumber(String.valueOf(c));
    }

    /**
     * Attempts to evaluate a postfix expression
     * @param postfix LinkedList of Strings representing an expression in
     *                postfix form
     * @return true if the expression was successfully evaluated
     */
    private boolean evaluate(LinkedList<String> postfix) {
        LinkedList<String> stack = new LinkedList<>();
        String current, calcResult, a, b;

        while(!postfix.isEmpty()) {
            current = postfix.removeFirst();

            if(isNumber(current)) {
                stack.push(current);
            } else {
                try {
                    b = stack.pop();
                    if(current.equals("_")) {
                        stack.push(negate(b));
                    } else {
                        a = stack.pop();
                        current = performCalculation(a, b, current);
                        stack.push(current);
                    }
                } catch(NumberFormatException|EmptyStackException|
                        NoSuchElementException e) {
                    errorMsg = "Evaluation error";
                    return false;
                } catch(InvalidExpressionException e) {
                    errorMsg = e.getMessage();
                    return false;
                } catch(ArithmeticException e) {
                    errorMsg = "Arithmetic overflow error";
                    return false;
                }
            }
        }

        try {
            result = Double.parseDouble(stack.pop());
            return true;
        } catch(NumberFormatException|EmptyStackException e) {
            errorMsg = "Evaluation error.";
            return false;
        }
    }

    /**
     * Attempts to perform a calculation between two numbers
     * @param a String representation of the first number
     * @param b String representation of the second number
     * @param operator String representing the mathematical operator
     * @return String containing the result of the expression
     * @throws InvalidExpressionException Thrown when the expression is invalid,
     *      such as division by zero or an unknown operator
     * @throws NumberFormatException thrown when either a or b cannot be
     *      converted to a floating-point number
     */
    private String performCalculation(String a, String b, String operator)
            throws InvalidExpressionException, NumberFormatException {
        double operationResult;
        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);

        switch(operator) {
            case "^":
                operationResult = Math.pow(num1, num2);
                if(Double.isInfinite(operationResult)) {
                    throw new InvalidExpressionException(
                            "Arithmetic overflow error.");
                }
                break;
            case "*":
                operationResult = num1 * num2;
                break;
            case "/":
                if(num2 == 0.0) {
                    throw new InvalidExpressionException(
                            "Cannot divide by zero");
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
                throw new InvalidExpressionException("Error: unknown operator");
        }

        return String.valueOf(operationResult);
    }
    private String negate(String operand) throws NumberFormatException{
        return String.valueOf(Double.parseDouble(operand) * -1);
    }
}