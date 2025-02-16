package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * ClearError operator
 */
public class ClearError extends Operator {
    /**
     * Redefine the execute method to clear the error state of the calculator
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        state.clearError();
    }
}
