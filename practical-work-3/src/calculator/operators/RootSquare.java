package calculator.operators;

/**
 * RootSquare operator
 */
public class RootSquare extends UnaryOperator {
    /**
     * Redefinition of the calculate method for the square root
     * @param operand
     * @return
     */
    @Override
    public double calculate(double operand) {
        if (operand < 0) throw new ArithmeticException("Square root of negative number");
        return Math.sqrt(operand);
    }
}
