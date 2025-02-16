package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * DecimalPoint operator
 */
public class DecimalPoint extends Operator {
    /**
     * Redefinition of the execute method for decimal point
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        if (!state.getNewValue().contains(".")) state.setNewValue(state.getNewValue() + ".");
    }
}
