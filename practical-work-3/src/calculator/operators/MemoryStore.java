package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * MemoryStore operator
 */
public class MemoryStore extends Operator {
    /**
     * Redefining the execute method to store the new value in the memory
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        state.setMemoryValue(state.getNewValue());
        state.setNewValue("0");
    }
}
