package classes.objects.methods;

public class LearnInitNums {
    // static field (or member)
    private static final double PI = 3.14;

    public static int[] nums = initNums();

    // static initializer - a method with no name
//    static {
//        nums = new int[5];
//        nums[0] = 3;
//        nums[1] = 5;
//        nums[2] = 3;
//        nums[3] = 7;
//        nums[4] = 8;
//    }

    // Static initializer
    public static int[] initNums() {
        int[] nums = new int[5];
        nums[0] = 3;
        nums[1] = 5;
        nums[2] = 3;
        nums[3] = 7;
        nums[4] = 8;

        return nums;
    }

    private final String MY_BIG_CONSTANTS = "Hi, this is my constant message";

    public void sayHello() {
        System.out.println("Hello");
    }

    public static void main(String[] args) {
        LearnInitNums p1 = new LearnInitNums();
        p1.sayHello();
        System.out.println(PI);
    }

    public static class ArrayDemo {
        public static void main(String[] args) {

            int[] nums = {10, 20, 30, 40, 50};
            System.out.println(nums.length);
            System.out.println(nums[2]);
        }
    }
}
