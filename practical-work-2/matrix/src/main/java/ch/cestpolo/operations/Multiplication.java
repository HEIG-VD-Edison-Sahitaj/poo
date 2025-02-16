package ch.cestpolo.operations;

/**
 * Class representing the multiplication of two operands
 */
public class Multiplication extends Operation {
    public Multiplication() {
        symbol = "x";
    }

    /**
     * Execute the multiplication of the two operands
     * @param rhs First operand
     * @param lhs Second operand
     * @return The product of the two operands
     */
    @Override
    public int apply(int rhs, int lhs) {
        return rhs * lhs;
    }
}
