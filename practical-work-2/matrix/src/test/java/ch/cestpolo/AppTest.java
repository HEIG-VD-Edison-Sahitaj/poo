package ch.cestpolo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for the Main class
 */
class AppTest {
    @Test
    public void testMain() {
        String[][] args = {
            {"3", "4", "3", "5", "5"}, // Correct
            {"3", "4", "3", "5", "abc"}, // Incorrect modulus type
            {"3", "4", "3", "5", "3.14"}, // Incorrect modulus type
            {"3", "4", "3", "5"}, // No modulus
        };
        for (String[] arg : args) {
            System.out.println("Testing with arguments: " + String.join(" ", arg));
            Main.main(arg);
        }
    }

}