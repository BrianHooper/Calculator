import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {

    @Test
    void expressionTest() {
        Equation eq = new Equation();

        // Addition test
        eq.addOperand("5+5");
        eq.calculate();
        assertEquals("= 10",  eq.getResult());
        eq.clear();

        // Subtraction test
        eq.addOperand("5-10");
        eq.calculate();
        assertEquals("= -5",  eq.getResult());
        eq.clear();

        // Multiplication test
        eq.addOperand("5*10");
        eq.calculate();
        assertEquals("= 50",  eq.getResult());
        eq.clear();

        // Division test
        eq.addOperand("5/2");
        eq.calculate();
        assertEquals("= 2.5",  eq.getResult());
        eq.clear();

        // Power test
        eq.addOperand("2^2");
        eq.calculate();
        assertEquals("= 4",  eq.getResult());
        eq.clear();

        // Unary operator test
        eq.addOperand("2--2");
        eq.calculate();
        assertEquals("= 4",  eq.getResult());
        eq.clear();

        // Multiplying two negative numbers
        eq.addOperand("-5*-5");
        eq.calculate();
        assertEquals("= 25", eq.getResult());
        eq.clear();

        // Implied multiplication with parenthesis
        eq.addOperand("(5)(5)");
        eq.calculate();
        assertEquals("= 25", eq.getResult());
        eq.clear();
    }

    @Test
    void addOperandTest() {
        Equation eq = new Equation();
        eq.addOperand("(");
        eq.addOperand("5");
        eq.addOperand("(");
        eq.addOperand("-");
        eq.addOperand("2");
        eq.addOperand(")");
        eq.addOperand("-");
        eq.addOperand("-");
        eq.addOperand("4");
        eq.addOperand(")");
        assertEquals("(5(-2)--4)", eq.getExpression());
    }
}