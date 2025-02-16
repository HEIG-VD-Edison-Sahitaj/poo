package calculator.operators;

/**
 * Subtraction operator
 */
public class Subtraction extends BinaryOperators {
    /**
     * Redefinition of the calculate method for the subtraction operator
     * @param operand1
     * @param operand2
     * @return
     */
    @Override
    public double calculate(double operand1, double operand2) {
        return operand1 - operand2;
    }
}
