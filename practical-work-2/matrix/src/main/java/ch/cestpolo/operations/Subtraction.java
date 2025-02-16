package ch.cestpolo.operations;

/**
 * Class representing the subtraction of two operands
 */
public class Subtraction extends Operation {
    public Subtraction() {
        symbol = "-";
    }

    /**
     * Execute the subtraction of the two operands
     * @param rhs First operand
     * @param lhs Second operand
     * @return The difference of the two operands
     */
    @Override
    public int apply(int rhs, int lhs) {
        return rhs - lhs;
    }
}
