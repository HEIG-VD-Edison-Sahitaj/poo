package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Digit operator
 */
public class Digit extends Operator {
    private final int digit;

    /**
     * Constructor for the Digit class
     * @param digit The digit to be added to the calculator's display.
     */
    public Digit(int digit) {
        this.digit = digit;
    }

    /**
     * Redefinition of the execute method for the Digit class
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        state.getExecutionMode().handleDigit(state, String.valueOf(digit));
    }
}
