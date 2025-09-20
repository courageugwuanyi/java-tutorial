package regular.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnRegexPart2 {
    public static void main(String[] args) {
        // ?:(group) this notation means consider only the inner group. i.e. the group directly after the notation.
        // place the (?<nameOfGroup>REGEX)
        String phoneNumberRegex = """
                # This is my regex to parse the parts of a phone number
                (?:\\(?(?<countryCode>\\d{1,3})\\)??[-.,\\s])? # Get's country code
                (?<areaCode>\\d{3})?[-.,\\s]? # Get's area code
                (?:(?<exchange>\\d{1,3})[-.,\\s])? # Get's exchange
                (?<lineNumber>\\d{4}) # Get's Line code
                """;
        String phoneNumber = "44.232.333.2365";
        System.out.println(phoneNumber.matches(phoneNumberRegex));

        // Human readable code to Byte Code
        // Pattern.COMMENTS allows us to include comment in our regex.
        // It also prevents us from including literal spaces in our Regex
        // (e.g. "I am Courage", instead we will have "I\\sam\\sCourage")
        Pattern pat = Pattern.compile(phoneNumberRegex, Pattern.COMMENTS);
        Matcher mat = pat.matcher(phoneNumber);

        if (mat.matches()) {
            System.out.println(mat.group(0));
            System.out.format("Country code: %s \n", mat.group("countryCode"));
            System.out.format("Area code: %s \n", mat.group("areaCode"));
            System.out.format("Exchange: %s \n", mat.group("exchange"));
            System.out.format("Line number: %s \n", mat.group("lineNumber"));
        }
    }

    public static class ParseRealText {
        public static void main(String[] args) {

        }
    }
}
