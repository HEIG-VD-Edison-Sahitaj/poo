package ch.cestpolo.operations;

/**
 * Abstract class for operations
 */
public abstract class Operation {
    protected String symbol;

    /**
     * Execute the operation on the two operands
     * @param rhs First operand
     * @param lhs Second operand
     * @return The result of the operation
     */
    public abstract int apply(int rhs, int lhs);

    /**
     * Return the symbol of the operation
     * @return The symbol of the operation
     */
    @Override
    public String toString() {
        return symbol;
    }
}
