package numbers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimals {
    public static void main(String[] args) {
        /* The whole point of using BigDecimals is to achieve maximum accuracy or precision */

        System.out.println("Arithmetic operations with floats:");
        float num1 = 6.15f;
        float num2 = 3.178f;
        System.out.println(num1-num2);
        System.out.println();

        System.out.println("Creating BigDecimal object:");
        BigDecimal num3 = new BigDecimal("10.89");
        BigDecimal num4 = new BigDecimal("2.57");
        System.out.println(num3.subtract(num4));
        System.out.println();


        System.out.println("Creating MathContext object:");
        // Specify the rounding Mode to resolve the exception by creating a MathContext
        MathContext mc = new MathContext(2, RoundingMode.HALF_UP);
        System.out.println(new BigDecimal(".03965").divide(new BigDecimal("67.478"), mc));
        System.out.println();

        System.out.println("Maths operations using BigDecimals:");
        System.out.println(new BigDecimal("49").sqrt(mc));
        System.out.println(new BigDecimal("49").max(new BigDecimal("49.4")));
        System.out.println(new BigDecimal("-78").abs(mc));
        System.out.println(new BigDecimal("23").remainder(new BigDecimal("3", mc)));
        System.out.println();

        System.out.println("More on BigDecimal and BigInteger:");
        byte myByte = 100;
        BigDecimal num5 = new BigDecimal(myByte);
        byte b = num5.byteValue();
        System.out.println(b);
        System.out.println(num5.toString());

        /* The whole point of BigInteger is that it is able of rep integers much larger
        than any of the other primitive dataTypes for numbers  */
        BigInteger myInteger = new BigInteger("10.9787");
        System.out.println(myInteger);

    }
}
