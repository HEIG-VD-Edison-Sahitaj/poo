package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Abstract class for binary operators
 */
public abstract class BinaryOperators extends Operator {
    /**
     * Redefines the execute method to pop two values from the stack and calculate the result
     * @param state The state of the calculator
     */
    @Override
    public void execute(State state) {
        if (!state.getStack().isEmpty()) {
            double operand1 = parseOperand(state);
            double operand2 = state.getStack().isEmpty() ? 0 : state.popValue();
            try {
                double result = calculate(operand1, operand2);
                state.setNewValue(String.valueOf(result));
                state.setResultDisplayed(true);
            } catch (ArithmeticException e) {
                state.setError(e.getMessage());
            }
        } else state.setError("Stack is empty");
    }

    /**
     * Calculate the result of the operation
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The result of the operation
     */
    protected abstract double calculate(double operand1, double operand2);
}
