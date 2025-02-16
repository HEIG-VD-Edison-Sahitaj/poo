package calculator.operators;

import static java.lang.Math.pow;

/**
 * Square operator
 */
public class Square extends UnaryOperator {
    /**
     * Redefinition of the calculate method for the square operator
     * @param operand
     * @return
     */
    @Override
    public double calculate(double operand) {
        return pow(operand, 2);
    }
}
