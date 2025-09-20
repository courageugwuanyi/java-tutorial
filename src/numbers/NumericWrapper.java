package numbers;

public class NumericWrapper {

    /**
     * Takes in any type of dataType at all and stores it somewhere generically
     *
     * All classes in java extends a superclass called object.
     */
    public static void storeData(Object obj){
        // Do something

    }

    public static void staffRecord(String[] args) {
        int age = Integer.parseInt(args[0]);
        double amount = Double.parseDouble(args[1]);
        boolean flag = Boolean.parseBoolean(args[2]);

        System.out.printf("You will be %d years old in 15years. %n", age);
    }

    public static void main(String[] args) {
        int num1 = 7;

        // Numeric Wrapper Types (or Boxing)
        Integer num2 = Integer.valueOf(num1); // auto-boxing
        Double myDouble = Double.valueOf("10.05");
        Float myFloat = Float.valueOf("23.3f");
        Byte myByte = Byte.valueOf("1");

        // converts the int to double
        Double num3 = num2.doubleValue();
        storeData(num2);
        System.out.println(num3);

        staffRecord(new String[]{"20", "100", "false"});
    }
}
