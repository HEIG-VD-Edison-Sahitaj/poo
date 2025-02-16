package calculator.operators;

import calculator.core.Operator;
import calculator.core.State;

/**
 * Abstract class for unary operators
 */
public abstract class UnaryOperator extends Operator {
    /**
     * Redefines the execute method to pop one value from the stack and calculate the result
     * @param state the state of the calculator
     */
    @Override
    public void execute(State state) {
            double operand = parseOperand(state);
            try {
                double result = calculate(operand);
                state.setNewValue(String.valueOf(result));
                state.setResultDisplayed(true);
            } catch (ArithmeticException e) {
                state.setError(e.getMessage());
            }
    }

    /**
     * Calculate the result of the operation
     * @param operand The operand
     * @return The result of the operation
     */
    protected abstract double calculate(double operand);
}
