package calculator.core;

import calculator.operators.*;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Console-based calculator application.
 */
public class Calculator {
    private final State state;
    private final HashMap<String, Operator> operators; // HashMap with operator name as key and Operator object as value

    /**
     * Constructor to initialize the state and operators HashMap.
     */
    public Calculator() {
        this.state = new State(new ConsoleMode());
        this.operators = new HashMap<>();
        initializeOperators();
    }

    /**
     * Initialize the operators HashMap with the operator name as key and Operator object as value.
     */
    private void initializeOperators() {
        operators.put("+", new Addition());
        operators.put("-", new Subtraction());
        operators.put("*", new Multiplication());
        operators.put("/", new Division());
        operators.put("sqrt", new RootSquare());
        operators.put("x^2", new Square());
        operators.put("1/x", new Reciprocal());
        operators.put("CE", new ClearError());
        operators.put("C", new Clear());
        operators.put("MS", new MemoryStore());
        operators.put("MR", new MemoryRecall());
        // Add the digit operators to the HashMap
        for (int i = 0; i <= 9; i++) {
            operators.put(String.valueOf(i), new Digit(i));
        }
    }

    /**
     * Method to run the calculator application.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("java Calculator");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) break;
            processInput(input);
            displayState();
        }
        scanner.close();
    }

    /**
     * Process the input by checking if the input is an operator or a numeric value.
     * @param input user input
     */
    private void processInput(String input) {
        if (state.getError() != null) {
            handleErrorState(input);
        } else if (operators.containsKey(input)) {
            operators.get(input).execute(state);
        } else {
            handleNumericInput(input);
        }
    }

    /**
     * Handle the error state by checking if the input is 'CE' or 'C' to clear the error.
     * @param input user input
     */
    private void handleErrorState(String input) {
        if (input.equals("CE") || input.equals("C")) {
            operators.get(input).execute(state);
        } else {
            System.out.println("Only 'CE' or 'C' can clear the error.");
        }
    }

    /**
     * Handle the numeric input by pushing the new value to the stack.
     * @param input user input
     */
    private void handleNumericInput(String input) {
        try {
            double value = Double.parseDouble(input);
            // If the new value is not 0, push the new value to the stack
            if (!state.getNewValue().equals("0")) {
                state.pushValue(Double.parseDouble(state.getNewValue()));
            }
            state.setNewValue(String.valueOf(value));
        } catch (NumberFormatException e) {
            state.setError("Invalid input");
        }
    }

    /**
     * Display the state of the calculator.
     */
    private void displayState() {
        if (state.getError() != null) {
            System.out.println(state.getError());
        } else {
            displayNewValue();
            displayStack();
        }
    }

    /**
     * Display the new value if it is not 0.
     */
    private void displayNewValue() {
        if (!state.getNewValue().equals("0")) {
            System.out.print(Double.parseDouble(state.getNewValue()) + " ");
        }
    }

    /**
     * Display the stack values.
     */
    private void displayStack() {
        for (Double value : state.getStack()) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     * Main method to run the calculator application.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        new Calculator().run();
    }
}
