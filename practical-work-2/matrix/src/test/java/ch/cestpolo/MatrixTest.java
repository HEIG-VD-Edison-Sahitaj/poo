package ch.cestpolo;

import ch.cestpolo.operations.Addition;
import ch.cestpolo.operations.Multiplication;
import ch.cestpolo.operations.Subtraction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Matrix class
 */
class MatrixTest {

    /**
     * Test the general cases for the matrix operations
     */
    @Test
    public void generalTestCases() {
        Matrix matrix1 = new Matrix(new int[][]{
                {1, 3, 1, 1},
                {3, 2, 4, 2},
                {1, 0, 1, 0}}, 5);
        Matrix matrix2 = new Matrix(new int[][]{
                {1, 4, 2, 3, 2},
                {0, 1, 0, 4, 2},
                {0, 0, 2, 0, 2}}, 5);
        Matrix resultAddition = new Matrix(new int[][]{
                {2, 2, 3, 4, 2},
                {3, 3, 4, 1, 2},
                {1, 0, 3, 0, 2}}, 5);
        Matrix resultSubtraction = new Matrix(new int[][]{
                {0, 4, 4, 3, 3},
                {3, 1, 4, 3, 3},
                {1, 0, 4, 0, 3}}, 5);
        Matrix resultMultiplication = new Matrix(new int[][]{
                {1, 2, 2, 3, 0},
                {0, 2, 0, 3, 0},
                {0, 0, 2, 0, 0}}, 5);
        assertEquals(resultAddition.toString(), matrix1.applyOperation(matrix2, new Addition()).toString());
        assertEquals(resultSubtraction.toString(), matrix1.applyOperation(matrix2, new Subtraction()).toString());
        assertEquals(resultMultiplication.toString(), matrix1.applyOperation(matrix2, new Multiplication()).toString());
    }

    @Test
    public void testRandomMatrixWith0Modulus() {
        assertThrows(RuntimeException.class, () -> new Matrix(2, 2, 0));
    }

    @Test
    public void testRandomMatrixWithNegativeModulus() {
        assertThrows(RuntimeException.class, () -> new Matrix(2, 2, -5));
    }

    @Test
    public void testRandomMatrixWith0Row() {
        assertThrows(RuntimeException.class, () -> new Matrix(0, 2, 5));
    }

    @Test
    public void testRandomMatrixWithNegativeRow() {
        assertThrows(RuntimeException.class, () -> new Matrix(-5, 2, 5));
    }

    @Test
    public void testRandomMatrixWith0Column() {
        assertThrows(RuntimeException.class, () -> new Matrix(2, 0, 5));
    }

    @Test
    public void testRandomMatrixWithNegativeColumn() {
        assertThrows(RuntimeException.class, () -> new Matrix(2, -5, 5));
    }

    @Test
    public void testRandomMatrixWith0RowAnd0Column() {
        assertThrows(RuntimeException.class, () -> new Matrix(0, 0, 5));
    }

    @Test
    public void testMatrixWith0Modulus() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 0}}, 0));
    }

    @Test
    public void testMatrixWithNegativeModulus() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 0}}, -5));
    }

    @Test
    public void testMatrixWithNullRow() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 0}, null}, 5));
    }

    @Test
    public void testMatrixWithDifferentLength() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 0}, {2}}, 5));
    }

    @Test
    public void testMatrixWithNegativeValue() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, -1}}, 5));
    }

    @Test
    public void testMatrixWithNullValue() {
        assertThrows(RuntimeException.class, () -> new Matrix(null, 5));
    }


    @Test
    public void testMatrixWithValueGreaterThanModulus() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 5}}, 5));
    }

    @Test
    public void testMatrixWithModulusLessThanValue() {
        assertThrows(RuntimeException.class, () -> new Matrix(new int[][]{{1, 4}, {3, 5}}, 4));
    }

    @Test
    public void testMatrixAdditionWithDifferentModulus() {
        Matrix matrix1 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 5);
        Matrix matrix2 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 6);
        assertThrows(RuntimeException.class, () -> matrix1.applyOperation(matrix2, new Addition()));
    }

    @Test
    public void testMatrixSubtractionWithDifferentModulus() {
        Matrix matrix1 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 5);
        Matrix matrix2 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 6);
        assertThrows(RuntimeException.class, () -> matrix1.applyOperation(matrix2, new Subtraction()));
    }

    @Test
    public void testMatrixMultiplicationWithDifferentModulus() {
        Matrix matrix1 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 5);
        Matrix matrix2 = new Matrix(new int[][]{{1, 4}, {3, 0}}, 6);
        assertThrows(RuntimeException.class, () -> matrix1.applyOperation(matrix2, new Multiplication()));
    }

}