package calculator.operators;

/**
 * Multiplication operator
 */
public class Multiplication extends BinaryOperators {
    /**
     * Redefinition of the calculate method for multiplication
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return
     */
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 * operand2;
    }
}
