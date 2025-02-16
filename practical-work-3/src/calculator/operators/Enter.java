package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Enter operator
 */
public class Enter extends Operator {
    /**
     * Redefinition of the execute method for the Enter operator
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
        state.pushValue(Double.parseDouble(state.getNewValue()));
        state.setNewValue("0");
        state.setResultDisplayed(false);
    }
}
