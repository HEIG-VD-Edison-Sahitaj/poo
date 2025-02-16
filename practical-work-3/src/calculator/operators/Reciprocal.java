package calculator.operators;

/**
 * Reciprocal operator
 */
public class Reciprocal extends UnaryOperator {
    /**
     * Redefinition of the calculate method for the reciprocal operator
     * @param operand The operand
     * @return The reciprocal of the operand
     */
    @Override
    public double calculate(double operand) {
        if (operand == 0) throw new ArithmeticException("Division by zero");
        return 1 / operand;
    }
}
