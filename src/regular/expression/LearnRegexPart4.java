package regular.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnRegexPart4 {
    private static String people = """
            Flinstone, Fred, 1/1/1900
            Rubble, Barney, 2/2/1905
            Flinstone, Wilma, 3/3/1910
            Rubble, Betty, 4/4/1915
            """;

    static String regex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4})\\n";
    public static void main(String[] args) {
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(people);

        mat.find();
        System.out.println(mat.group("firstName"));
        System.out.println(mat.group("lastName"));
        System.out.println(mat.group("dob"));
        System.out.println();

        System.out.println(mat.hasMatch());
        // gets the start index of the group
        System.out.println(mat.start("firstName"));
        // gets the end index of the group
        System.out.println(mat.end("lastName"));

        mat.find();
        System.out.println(mat.group("firstName"));
        System.out.println(mat.group("lastName"));
        System.out.println(mat.group("dob"));
        System.out.println();

        System.out.println(mat.hasMatch());
        // gets the start index of the group
        System.out.println(mat.start("firstName"));
        // gets the end index of the group
        System.out.println(mat.end("lastName"));
    }
}
