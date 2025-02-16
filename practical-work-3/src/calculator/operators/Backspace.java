package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Backspace operator.
 */
public class Backspace extends Operator {
    /**
     * Redefinition of the execute method. It removes the last character of the new value.
     * @param state The state of the calculator.
     */
    @Override
    public void execute(State state) {
        String value = state.getNewValue();
        if ((value != null) && (!value.isEmpty())) {
            value = value.substring(0, value.length() - 1);
            state.setNewValue(value.isEmpty() ? "0" : value);
        }
    }
}
