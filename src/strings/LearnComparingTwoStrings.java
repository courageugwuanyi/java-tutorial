package strings;

public class LearnComparingTwoStrings {
    public static void main(String[] args) {

        // String and StringBuffer are of different dataTypes but both have something in common
        // Both of them are character sequences
        String firsText = "Apple";
        String secondText = "apple";
        StringBuilder thirdText = new StringBuilder("Apple");

        // Allow you to compare both Strings and StringBuilder DataTypes. It is more flexible than equals().
        System.out.println(firsText.contentEquals(thirdText));
        System.out.println(firsText.contentEquals(secondText));

        // Allow you to compare only strings. Both items to be compared must be Strings
        System.out.println(firsText.equals(thirdText));
    }
}
