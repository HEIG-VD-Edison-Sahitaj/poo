package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * MemoryRecall operator
 */
public class MemoryRecall extends Operator {
    /**
     * Redefinition of the execute method for the MemoryRecall operator
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        state.setNewValue(state.getMemoryValue());
    }
}
