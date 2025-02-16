package calculator.operators;

/**
 * Division operator
 */
public class Division extends BinaryOperators {
    /**
     * Redefinition of the calculate method for the division
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The result of the division
     */
    @Override
    public double calculate(double operand1, double operand2) {
        if (operand2 == 0) throw new ArithmeticException("Division by zero");
        return operand1 / operand2;
    }
}
