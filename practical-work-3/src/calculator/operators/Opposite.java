package calculator.operators;

/**
 * Class to represent the opposite operation
 */
public class Opposite extends UnaryOperator {
    /**
     * Redefinition of the calculate method to calculate the opposite of the operand
     * @param operand
     * @return
     */
    @Override
    protected double calculate(double operand) {
        return -operand;
    }
}
