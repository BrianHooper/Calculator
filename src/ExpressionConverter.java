import java.util.LinkedList;
import java.util.Stack;

class ExpressionConverter {

    /**
     * Converts an expression in infix form to a LinkedList of Strings
     * where each element is either a number or an operator
     *
     * @param infix String element representing an infix expression
     * @return LinkedList of Strings
     * @throws NumberFormatException throws exception if a number is invalid
     */
    public static LinkedList<String> toLinkedList(String infix) throws
            NumberFormatException {
        StringBuilder numberBuilder = new StringBuilder();
        LinkedList<String> ifList = new LinkedList<>();

        // Iterate over each character in the infix string
        for (int i = 0; i < infix.length(); i++) {
            // Retrieve a character from the infix string
            char curChar = infix.charAt(i);

            // If the character is an operator, add it to the pfList
            if (!isNumber(curChar)) {
                if(curChar == '.') {
                    numberBuilder.append(curChar);
                    numberBuilder.insert(0, '-');
                } else {
                    // If there is a number currently being built
                    // Add it to the pfList and clear the builder
                    if (numberBuilder.length() > 0) {
                        // Check that the number in the builder is valid
                        isNumber(numberBuilder.toString());
                        ifList.add(numberBuilder.toString());
                        numberBuilder = new StringBuilder();
                    }

                    // Automatically add implied multiplication operator
                    // When parenthesis are used
                    if (curChar == '(' && !ifList.isEmpty() && isNumber(ifList.getLast())) {
                        ifList.add("*");
                        ifList.add(String.valueOf(curChar));
                    } else if (curChar == ')' && i < infix.length() - 1 &&
                            (isNumber(String.valueOf(infix.charAt(i + 1))) || infix.charAt(i + 1) == '(')) {
                        ifList.add(String.valueOf(curChar));
                        ifList.add("*");
                    } else {
                        ifList.add(String.valueOf(curChar));
                    }
                }

            } else {
                // Append the current character to the numberBuilder
                numberBuilder.append(curChar);
            }
        }

        // Make sure the numberBuilder is cleared
        if (numberBuilder.length() > 0) {

            // Check that the number in the builder is valid
            isNumber(numberBuilder.toString());
            ifList.add(numberBuilder.toString());
        }

        return ifList;
    }

    /**
     * Converts an infix LinkedList expression to a
     * postfix LinkedList expression
     *
     * @param infix LinkedList of Strings representing the infix expression
     * @return LinkedList of Strings as a postfix expression
     */
    public static LinkedList<String> toPostFix(LinkedList<String> infix) throws
            InvalidExpressionException {
        LinkedList<String> postfix = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        String currentElement;
        String lastElement = null;

        // Iterate over each element in the infix list
        while (infix.size() > 0) {
            // Pop the first element from the infix list
            currentElement = infix.removeFirst();

            if (currentElement.equals("(")) {
                // Push the ( to the stack
                stack.push(currentElement);
            } else if (currentElement.equals(")")) {
                // Pop the operands off the stack until a ) is encountered
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) stack.pop();
            } else if (isNumber(currentElement)) {
                // Append the number to the postfix list
                postfix.add(currentElement);
                if (!stack.isEmpty() && stack.peek().equals("_")) {
                    postfix.add(stack.pop());
                }
            } else {
                if (currentElement.equals("-") &&
                        ((lastElement == null) || (!lastElement.equals(")") && !isNumber(lastElement)))) {
                    // Append the special negation operator to the stack
                    stack.push("_");
                } else {
                    // Append the operators on the stack to the postfix list
                    // Until a lower precedence operator is encountered
                    while (!stack.isEmpty() && precedence(currentElement,
                            stack.peek())) {
                        postfix.add(stack.pop());
                    }
                    stack.push(currentElement);
                }
            }
            lastElement = currentElement;
        }

        // Add any remaining operators in the stack to the postfix list
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new InvalidExpressionException();
            } else postfix.add(stack.pop());
        }

        return postfix;
    }

    /**
     * Checks if one input has precedence over the other
     * using ^ greater than * / greater than + -
     *
     * @param a String operator
     * @param b String operator
     * @return true if a > b
     */
    private static boolean precedence(String b, String a) {
        return value(a) >= value(b);
    }

    /**
     * Applies a value to an operator
     *
     * @param s The operator as a String
     * @return int value representing the operators precedence
     */
    private static int value(String s) {
        switch (s) {
            case "_": return 6;
            case "^": return 5;
            case "*": return 4;
            case "/": return 3;
            case "+": return 2;
            case "-": return 1;
            default: return 0;
        }
    }

    /**
     * Checks that the input can be expressed as a valid
     * floating-point number
     *
     * @param s String input
     * @return true if the input is a number
     */
    public static boolean isNumber(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Checks that the input can be expressed as a valid
     * floating-point number
     *
     * @param c Character input
     * @return true if the input is a number
     */
    public static boolean isNumber(char c) {
        return isNumber(String.valueOf(c));
    }
}
