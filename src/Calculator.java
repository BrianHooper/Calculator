class Calculator {

    /**
     * Begins the program by creating a new View object
     * for displaying the GUI and a new Equation model for
     * performing the calculations
     */
    private void run() {
        new View(new Equation());

    }

    public static void main(String[] args) {
        new Calculator().run();
    }


}
