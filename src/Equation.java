import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Equation {
    private StringBuilder expression;
    private String errorMsg;
    private double result;
    private int size;
    private final int maxLength = 20;

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
    private LinkedList<String> toLinkedList(String infix) throws NumberFormatException{
        StringBuilder numberBuilder = new StringBuilder();
        LinkedList<String> ifList = new LinkedList<>();

        // Iterate over each character in the infix string
        for(int i = 0; i < infix.length(); i++) {
            // Retrieve a character from the infix string
            char curChar = infix.charAt(i);

            // If the character is an operator, add it to the pfList
            if(isOperator(curChar)) {

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
    private LinkedList<String> toPostFix(LinkedList<String> infix) throws InvalidExpressionException{
        LinkedList<String> postfix = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        String currentElement;

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
            } else {
                if(stack.isEmpty()) {
                    // Push the operator to the stack
                    stack.push(currentElement);
                } else {
                    // Append the operators on the stack to the postfix list
                    // Until a lower precedence operator is encountered
                    while(!stack.isEmpty() && !isParenthesis(stack.peek())
                            && precedence(currentElement, stack.peek())) {
                        postfix.add(stack.pop());
                    }
                    stack.push(currentElement);
                }
            }
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
     * Checks if the input is a parenthesis
     * @param s String input
     * @return true if input was a single parenthesis
     */
    private boolean isParenthesis(String s) {
        return s.equals("(") || s.equals(")");
    }

    /**
     * Checks if a character is an operator
     * @param c The character to check
     * @return true if the character is an operator
     */
    private boolean isOperator(char c) {
        switch(c) {
            case '(':
                return true;
            case ')':
                return true;
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;
            case '^':
                return true;
            default:
                return false;
        }
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
     * doubleing-point number
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

    private boolean evaluate(LinkedList<String> postfix) {
        Stack<String> stack = new Stack<>();
        String current, calcResult, a, b;

        while(!postfix.isEmpty()) {
            current = postfix.removeFirst();

            if(isNumber(current)) {
                stack.push(current);
            } else {
                try {
                    b = stack.pop();
                    a = stack.pop();
                    current = performCalculation(a, b, current);
                    stack.push(current);
                } catch(NumberFormatException|EmptyStackException e) {
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

    private String performCalculation(String a, String b, String operator)
            throws InvalidExpressionException, NumberFormatException, ArithmeticException {

        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);
        double result = 0.0;

        switch(operator) {
            case "^":
                result = Math.pow(num1, num2);
                if(Double.isInfinite(result)) {
                    throw new InvalidExpressionException("Arithmetic overflow error.");
                }
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if(num2 == 0.0) {
                    throw new InvalidExpressionException("Cannot divide by zero");
                }
                result = num1 / num2;
                break;
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
        }

        return String.valueOf(result);
    }
}
