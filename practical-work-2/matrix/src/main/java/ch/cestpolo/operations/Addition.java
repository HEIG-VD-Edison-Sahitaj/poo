package ch.cestpolo.operations;

/**
 * Class representing the addition of two operands
 */
public class Addition extends Operation {
    public Addition() {
        symbol = "+";
    }

    /**
     * Execute the addition of the two operands
     * @param rhs First operand
     * @param lhs Second operand
     * @return The sum of the two operands
     */
    @Override
    public int apply(int rhs, int lhs) {
        return rhs + lhs;
    }
}
