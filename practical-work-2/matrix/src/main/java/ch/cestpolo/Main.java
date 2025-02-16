package ch.cestpolo;

import ch.cestpolo.operations.Addition;
import ch.cestpolo.operations.Multiplication;
import ch.cestpolo.operations.Operation;
import ch.cestpolo.operations.Subtraction;

/**
 * Main class to run the matrix operations
 */
public class Main {
    final static int ARGUMENTS = 5;

    public static void main(String[] args) {
        try {
            if (args.length < ARGUMENTS) {
                throw new RuntimeException("Error: Not enough arguments");
            }
            int n1 = Integer.parseInt(args[0]);
            int m1 = Integer.parseInt(args[1]);
            int n2 = Integer.parseInt(args[2]);
            int m2 = Integer.parseInt(args[3]);
            int modulus = Integer.parseInt(args[4]);
            System.out.println("The modulus is " + modulus);
            Matrix matrix1 = new Matrix(n1, m1, modulus);
            Matrix matrix2 = new Matrix(n2, m2, modulus);

            System.out.println("one: \n" + matrix1 + "\n" + "two: \n" + matrix2);
            Operation[] operations = new Operation[] { new Addition(),
                    new Subtraction(), new Multiplication()};
            for(Operation op : operations) {
                System.out.println("one " + op + " two:" + "\n" + matrix1.applyOperation(matrix2, op));
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}