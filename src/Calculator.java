public class Calculator {

    public void run() {
        Equation eq = new Equation();
        eq.addOperand("((12+16)*((3156+(2+87))^(163+0)))");
        if(!eq.calculate()) {
            System.out.println(eq.getErrorMsg());
        }

    }

    public static void main(String[] args) {
        new Calculator().run();
    }
}
