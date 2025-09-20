package regular.expression;

public class LearnRegexPart1 {
    public static void main(String[] args) {
        System.out.println("Cat".matches("cat"));

        // character classes: [cC] = checks for both upper or lower case.
        // square bracket expressions
        System.out.println("cBat".matches("c[bB]at"));
        System.out.println("cBat".matches("[a-zA-Z][bB]at"));

        // invert square brackets; [^B] means = any character that is not a capital B.
        System.out.println("cBat".matches("[a-zA-Z][^B]at"));

        // use a negation for a range of characters
        System.out.println("cBat".matches("[a-zA-Z][^a-c]at"));

        // \w means word character. in JAVA you will need to use double backslash
        // \w = Matches any single word character or letter
        System.out.println("Lat".matches("\\wat"));

        // Match any 3 word characters includes: letters, numbers and underscore
        System.out.println("Lat".matches("\\w\\w\\w"));

        // \d matches any single 1 digit or number
        System.out.println("1Lat".matches("\\d\\wat"));

        // Matches any 2 digits or numbers
        System.out.println("10Lat".matches("\\d\\d\\wat"));

        // Using a quantifier
        System.out.println("242-333-7654".matches("\\d{3}-\\d{3}-\\d{4}"));

        // Allow any of hyphen or dot using square brackets. You can add more things inside the square bracket
        System.out.println("242.333.7654".matches("\\d{3}[.-]\\d{3}[-.]\\d{4}"));

        // Match only one whitespace using \s
        System.out.println("242 333 7654".matches("\\d{3}[-.\\s]\\d{3}[-.\\s]\\d{4}"));

        // Matches one or more spaces using the plus symbol infront of any of those characters inside the square brackets
        System.out.println("242 333 7654".matches("\\d{3}[-.\\s]+\\d{3}[-.\\s]+\\d{4}"));

        // Zero or more using the asterisks *
        System.out.println("242 3337654".matches("\\d{3}[-.\\s]*\\d{3}[-.\\s]*\\d{4}"));

        // Exactly Zero or 1 using question mark ?
        System.out.println("242 333  7654".matches("\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{4}"));

        // Min - Max range using {min,max}
        System.out.println("242 333 7344".matches("\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{3,4}"));

        // Min, with no upper limit using {min,}. i.e. at least 3 digits long
        System.out.println("242 333 44".matches("\\d{3}[-.\\s]?\\d{3}[-.\\s]?\\d{3,}"));

        // Remove duplication of pattern by using grouping technique. (repeated pattern){2} -- if same pattern occurs twice
        System.out.println("242 333 454".matches("(\\d{3}[-.\\s]?){2}\\d{3,}"));

        // Match the repeated pattern once or twice
        // Always try to read regex slowly from right to left and break them down into tiny pieces
        System.out.println("333 454".matches("(\\d{3}[-.\\s]?){1,2}\\d{3,}"));

        // Make something optional using zero or one --- ?
        System.out.println("1.232 333 454".matches("(\\d[.]?)?(\\d{3}[-.\\s]?){1,2}\\d{3,}"));


    }

}
