package numbers;

import java.security.SecureRandom;
import java.util.Random;

public class MathOperations {
    public static void main(String[] args) {
        int num1 = 7;
        int num2 = 4;
        double num3 = 3.57; // Has higher precision than float
        float num4 = 10.87f;

        System.out.println((float) num1 / num2);
        System.out.println((float) (num1 / num2));
        System.out.println(num3 + num4);
        System.out.println(8 % 5);
        System.out.println();

        int x = 1;
        System.out.println(x++);
        System.out.println(x);
        System.out.println();

        boolean myFlag = true;
        System.out.println(!myFlag);
        System.out.println();

        System.out.println("Math Operation Methods:");
        System.out.println(Math.abs(-7));
        // Ceiling: Rounds up to the nearest integer.
        System.out.println("Ceiling Method:");
        System.out.println(Math.ceil(5.0));
        System.out.println(Math.ceil(5.4));
        System.out.println(Math.ceil(5.6));
        System.out.println();
        //Floor the opposite of ceiling
        System.out.println("Floor Method");
        System.out.println(Math.floor(4.9));
        System.out.println(Math.floor(5.9));
        System.out.println(Math.floor(5.6));
        System.out.println();

        System.out.println("Raise to power of:");
        System.out.println(Math.pow(2, 3));
        System.out.println();

        System.out.println("Random method");
        System.out.println((int) (Math.random() * 10));
        System.out.println();

        System.out.println("Round numbers");
        System.out.println(Math.round(5.6));
        System.out.println(Math.round(5.4));
        System.out.println();

        System.out.println(Math.sqrt(81));
        System.out.println();

        System.out.println("Math constants");
        System.out.println(Math.PI);
        System.out.println(Math.E);
        System.out.println();

        System.out.println("Max and Min numbers");
        System.out.println(Math.max(num1, num2));
        System.out.println(Math.min(num3, num4));
        System.out.println();

        System.out.println("Ramdom Class");
        // This random objects takes a seed as the starting point.
        Random rand = new Random();
        System.out.println(rand.nextInt(10));
        System.out.println(rand.nextInt(5));
        System.out.println(rand.nextInt(10));
        System.out.println();

        System.out.println("SecureRandom Class");
        // The Secure Random is more secure than Random Class
        SecureRandom rand2 = new SecureRandom();
        System.out.println(rand2.nextInt(10));
        System.out.println(rand2.nextInt(10));
        System.out.println(rand2.nextInt(10));

    }
}
