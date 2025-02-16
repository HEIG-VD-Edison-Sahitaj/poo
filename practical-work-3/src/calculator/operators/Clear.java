package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Clear operator
 */
public class Clear extends Operator {
    /**
     * Redefine the execute method to clear the state
     * @param state the state
     */
    @Override
    public void execute(State state) {
        state.clear();
    }
}
