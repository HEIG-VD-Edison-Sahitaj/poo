package ch.cestpolo;

import ch.cestpolo.operations.Operation;

/**
 * Class representing a matrix
 */
public class Matrix {
    /**
     * Create a matrix from a 2D array of values and a modulus given
     * @param values The matrix given
     * @param modulus The modulus of the matrix
     * @throws RuntimeException If the matrix is null, has different number of columns or values are out of bounds
     */
    public Matrix(int[][] values, int modulus) {
        checkModulus(modulus);
        this.modulus = modulus;
        rows = values.length;
        columns = (values.length > 0 && values[0] != null) ? values[0].length : 0;
        this.matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            if (values[i] == null) {
                throw new RuntimeException("Row " + i + " is null");
            }

            if(values[i].length != columns) {
                throw new RuntimeException("Row " + i + " has " + values[i].length +
                        " columns, but the matrix must have " + columns + " columns");
            }
                for(int j = 0; j < columns; j++) {
                if(values[i][j] < 0 || values[i][j] >= modulus) {
                    throw new RuntimeException("Matrix values must be between 0 and " + (modulus - 1));
                }
                this.matrix[i][j] = values[i][j];
            }
        }
    }

    /**
     * Create a matrix with random values between 0 and modulus - 1
     * @param rows The number of rows
     * @param columns The number of columns
     * @param modulus The modulus of the matrix
     * @throws RuntimeException If the number of rows or columns is less or equal to 0
     */
    public Matrix(int rows, int columns, int modulus) {
        checkModulus(modulus);
        if(rows <= 0 || columns <= 0)
            throw new RuntimeException("Rows and columns must be greater than 0");

        this.modulus = modulus;
        this.rows = rows;
        this.columns = columns;
        matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = (int) (Math.random() * modulus);
            }
        }
    }

    /**
     * Apply an operation on two matrices
     * @param rhs The right-hand side matrix
     * @param op The operation to apply
     * @return The result of the operation
     * @throws RuntimeException If the matrices have different modulus
     */
    public Matrix applyOperation(Matrix rhs, Operation op) {
        if(rhs.modulus != this.modulus)
            throw new RuntimeException("Matrices must have the same modulus");
        int maxRows = Math.max(this.rows, rhs.rows);
        int maxColumns = Math.max(this.columns, rhs.columns);
        int[][] result = new int[maxRows][maxColumns];

        for(int i = 0; i < maxRows; i++) {
            for(int j = 0; j < maxColumns; j++) {
                int lhsValue = (i < this.rows && j < this.columns) ? this.matrix[i][j] : 0;
                int rhsValue = (i < rhs.rows && j < rhs.columns) ? rhs.matrix[i][j] : 0;
                result[i][j] = Math.floorMod(op.apply(lhsValue, rhsValue), modulus);
            }
        }
        return new Matrix(result, modulus);
    }

    /**
     * Check if the modulus is greater than 0 and throw an exception if it is not
     * @param modulus The modulus to check
     * @throws RuntimeException If the modulus is less or equal to 0
     */
    private void checkModulus(int modulus) {
        if(modulus <= 0)
            throw new RuntimeException("Modulus must be greater than 0");
    }

    /**
     * Return the string representation of the matrix
     * @return The string representation of the matrix
     */
    @Override
    public String toString() {
        if(rows == 0 && columns == 0) return "";
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            if(i > 0) result.append("\n");
            for(int j = 0; j < columns; j++) {
                result.append(matrix[i][j]);
                if(j < columns - 1) result.append(' ');
            }
        }
        return result.toString();
    }

    private final int[][] matrix;
    private final int rows;
    private final int columns;
    private final int modulus;
}
