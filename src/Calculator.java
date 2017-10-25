public class Calculator {

    public void run() {
        Equation eq = new Equation();
        eq.addOperand("0-(12.2+16)*(3^2+(2+87))-(5+2)");

        if(eq.calculate()) {
            System.out.println(eq.getResult());
        } else {
            System.out.println(eq.getErrorMsg());
        }

    }

    public static void main(String[] args) {
        new Calculator().run();
    }
}
