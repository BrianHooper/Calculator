public class Calculator {

    public void run() {
        Equation eq = new Equation();
        eq.addOperand("(1+2)*(3+4)/(12-5)");
        eq.backspace();
        eq.backspace();
        eq.calculate();
        System.out.println(eq.getResult());
    }

    public static void main(String[] args) {
        new Calculator().run();
    }
}
