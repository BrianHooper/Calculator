import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {
    @Test
    void unaryTestOne() {
        Equation eq = new Equation();
        eq.addOperand("-");
        eq.addOperand("5");
        eq.calculate();
        assertEquals("= -5", eq.getResult());
    }

    @Test
    void unaryTestTwo() {
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
        eq.calculate();
        assertEquals("= -6", eq.getResult());
    }
}