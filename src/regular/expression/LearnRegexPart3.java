package regular.expression;

public class LearnRegexPart3 {
    public static void main(String[] args) {
        // a dot matches any character at all
        // caret inside a square bracket, e.g. [^abc] means match anything that is not abc.
        // ^ outside a square bracket means the beginning of a line
        // $ means end of a line
        System.out.print("1: ");
        System.out.println("doggy".matches("^.....$"));

        // \\b boundary simply matches the area between a word character and a non-word character
        // for example in the string "cat doggy", there is a space between cat and doggy which matches \\s,
        // however, between the space and the start of doggy, there is a boundary which is matched by \\b
        System.out.print("2: ");
        System.out.println("cat doggy".matches("^...\\s\\b.....$"));

        // \\W matches anything other than word characters, however it can match a dash -
        System.out.print("3: ");
        System.out.println("cat".matches("\\W\\W\\W"));

        // \\D matches that is not a digits
        System.out.print("4: ");
        System.out.println("c-t".matches("\\D\\D\\D"));

        // \\S matches any single characters that isn't a space
        System.out.print("5: ");
        System.out.println("ca r".matches("\\D\\W\\S\\D"));
    }
}
