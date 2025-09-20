package classes.objects.methods;

public class MethodDemo {
    private String middleName = "Christopher";

//    A Block of code where work gets done
//    Can take 0 or more input and return 0 or 1 output
//    Where commands can be executed. A method is a function inside a class
//    Characteristics of a method: void = means does not return any data.
//    Method names = should always have a verb, starts with a lowercase,
//    Inside the method is a statement/declarations that are ended with a s. colon
    /**
     *
     * */
    public void sayHello() {
        System.out.println("Hello!");
    }

    public void saySomething(String message) {
        System.out.println(message);
    }

    public char getMiddleInitial() {
        return middleName.charAt(0);
    }

    public void test(int num1, int num2, String[] words) {
        // do something clever with words
    }

    public void test2(int num1, int num2, String... words) {
        // The use of var args

        System.out.println(words.length);

    }

    // static methods are methods that are attached to the class
    // They are used in utility classes. Utility classes mostly contain static methods.
    // They do not classes.objects.methods.business.model a concept.
    // A place to group useful capabilities or useful method together
    // You don't necessarily create an instance of that class


    public static void main(String[] args) {
        MethodDemo person = new MethodDemo();
        person.sayHello();
        person.saySomething("My groovy message ");
        char initialName = person.getMiddleInitial();
        System.out.println(initialName);

        person.test( 2, 2, new String[]{"one", "two", "three"});
        person.test2(5, 40, "four", "five", "six", "seven");
    }
}
