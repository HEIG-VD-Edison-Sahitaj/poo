package test;

import calculator.core.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit test for the Calculator class
 */
public class CalculatorTest {
    /**
     * Addition test
     */
    @Test
    void testAddition () {
            String input = "3\n5\n+\nexit\n";
            ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            Calculator calculator = new Calculator();
            calculator.run();
            String output = out.toString();
            assertTrue(output.contains("8.0"));
    }

    /**
     * Subtraction test
     */
    @Test
    void testSubtraction() {
        String input = "10\n4\n-\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("-6.0"));
    }

    /**
     * Multiplication test
     */
    @Test
    void testMultiplication() {
        String input = "3\n-5\n*\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("-15.0"));
    }

    /**
     * Division test
     */
    @Test
    void testDivision() {
        String input = "2\n10\n/\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("5.0"));
    }

    /**
     * Division by zero test
     */
    @Test
    void testDivisionByZero() {
        String input = "0.0\n2\n/\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("Division by zero"));
    }

    /**
     * Root square test
     */
    @Test
    void testSquareRoot() {
        String input = "9\nsqrt\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("3.0"));
    }

    /**
     * Negative root square test
     */
    @Test
    void testNegativeSquareRoot() {
        String input = "-9\nsqrt\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("Square root of negative number"));
    }

    /**
     * Square test
     */
    @Test
    void testSquare() {
        String input = "3\nx^2\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("9.0"));
    }

    /**
     * Reciprocal test
     */
    @Test
    void testReciprocal() {
        String input = "4\n1/x\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("0.25"));
    }

    /**
     * Reciprocal zero test
     */
    @Test
    void testReciprocalZero() {
        String input = "0\n1/x\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("Division by zero"));
    }

    /**
     * Clear error test with CE
     */
    @Test
    void testClearError() {
        String input = "0.0\n2\n/\nCE\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        String[] lines = output.split("\n");
        assertTrue(output.contains("Division by zero"), "Until CE, the output should contain 'Division by zero'");
        String lastLine = lines[lines.length - 2];
        assertFalse(lastLine.contains("Division by zero"), "After CE, the error 'Division by zero' should be cleared");
    }

    /**
     * Clear error test with C
     */
    @Test
    void testClearWithError() {
        String input = "0.0\n2\n/\nC\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        String[] lines = output.split("\n");
        assertTrue(output.contains("Division by zero"), "Until C, the output should contain 'Division by zero'");
        String lastLine = lines[lines.length - 2];
        assertFalse(lastLine.contains("Division by zero"), "After C, the error 'Division by zero' should be cleared");
    }

    /**
     * Clear test
     */
    @Test
    void testClear() {
        String input = "3\n5\n4\nC\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        String[] lines = output.split("\n");
        assertTrue(output.contains("3.0") && output.contains("5.0") && output.contains("4.0"), "Before C, the output should contain '3.0', '5.0', and '4.0'");
        String afterClear = lines[lines.length - 2];
        assertFalse(afterClear.contains("3.0") || afterClear.contains("5.0") || afterClear.contains("4.0"), "After C, the stack should be empty");
    }

    /**
     * Memory store test
     */
    @Test
    void testMemoryStore() {
        String input = "5\nMS\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        String[] lines = output.split("\n");
        String afterMS = lines[lines.length - 2];
        assertFalse(afterMS.contains("5.0"));
    }

    /**
     * Memory recall test
     */
    @Test
    void testMemoryRecall() {
        String input = "5\nMS\nMR\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        String[] lines = output.split("\n");
        assertFalse(lines[lines.length - 3].contains("5.0"));
        String afterMR = lines[lines.length - 2];
        assertTrue(afterMR.contains("5.0"));
    }

    /**
     * Stack empty test
     */
    @Test
    void testStackEmpty() {
        String input = "5\n+\nexit\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculator calculator = new Calculator();
        calculator.run();
        String output = out.toString();
        assertTrue(output.contains("Stack is empty"));
    }
}
