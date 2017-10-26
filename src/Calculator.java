public class Calculator {

    public void run() {
        View view = new View(new Equation());
    }

    public static void main(String[] args) {
        new Calculator().run();
    }
}
