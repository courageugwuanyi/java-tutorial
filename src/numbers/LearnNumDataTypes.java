package numbers;

public class LearnNumDataTypes {
    public static void main(String[] args) {
        byte myByte = 127;
        boolean myFlag = true;
        short myBigShort = 3267;
        char myA = 'A';
        char biggerShort = 64000;
        double myDouble = 3.141592;
        float myFloat = 3.141592F; // You need to put an F infront of a float (FLOATS approximation is less precise)

        float num1 = 2.15f;
        float num2 = 1.10f;

        double num3 = 2.15;
        double num4 = 1.10;

        System.out.println(num1 - num2);
        System.out.println(num3 - num4);
        System.out.println();

        // do calculation in hexademical, you will need to start with 0x
        // do calculation in binary, you will need to start with 0b
        byte anotherByte = 0x1f;
        int anotherInt = 0b110 & 0b001 | 0b1010 ;

        System.out.println(anotherInt);
    }
}
