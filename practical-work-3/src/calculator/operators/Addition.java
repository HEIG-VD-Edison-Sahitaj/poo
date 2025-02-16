package calculator.operators;

/**
 * Addition operator
 */
public class Addition extends BinaryOperators {
    /**
     * Redefines the calculate method to add two operands
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The sum of the two operands
     */
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 + operand2;
    }
}
