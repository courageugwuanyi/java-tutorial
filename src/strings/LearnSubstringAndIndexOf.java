package strings;

public class LearnSubstringAndIndexOf {
    public static void main(String[] args) {
        // Substring
         String myText = "apple";
        String firstPart = myText.substring(0, 1);
        String remainingText = myText.substring(1);
        String capitalizeFirstLetter = firstPart.toUpperCase();
        String myNewText = capitalizeFirstLetter.concat(remainingText);

        int capacity = capitalizeFirstLetter.length() + remainingText.length();
        String mySecondNewText = new StringBuilder(capacity)
                .append(capitalizeFirstLetter)
                .append(remainingText)
                .toString();

        System.out.println(mySecondNewText);

        // IndexOf
        String exampleText = "four score and seven years ago";

        // used for characters that are not easy to type - like special characters like space
        // Characters are searched by their unicode character values
        // ASCII only supports Latin characters, unicode generally supports many characters of many parts of the world
        // Every single character has an encoding attached to it.
        // -1 means there is no match.
        System.out.println(exampleText.indexOf(4));
        System.out.println(exampleText.indexOf("s", 6));
        System.out.println(exampleText.lastIndexOf("s"));

        String phoneNumber = "(234) 333-55881";
        String areaCode = parseAreaCode(phoneNumber);
        String exchange = parseExchange(phoneNumber);
        String lineNumber = parseLineNumber(phoneNumber);

        System.out.println("Area code: " + areaCode);
        System.out.println("Exchange: " +exchange);
        System.out.println("Line number: " +lineNumber);
    }

    public static String parseAreaCode(String phoneNumber) {
        int openingBracket = phoneNumber.indexOf("(");
        int closingBracket = phoneNumber.indexOf(")");
        int beginIndex = openingBracket + 1;
        return phoneNumber.substring(beginIndex, closingBracket);
    }
    public static String parseExchange(String phoneNumber) {
        int spaceIndex = phoneNumber.indexOf(" ");
        int hyphenIndex = phoneNumber.indexOf("-");
        int beginIndex = spaceIndex + 1;
        return phoneNumber.substring(beginIndex, hyphenIndex);
    }

    public static String parseLineNumber(String phoneNumber) {
        int hyphenIndex = phoneNumber.indexOf("-");
        int beginIndex = hyphenIndex + 1;
        return phoneNumber.substring(beginIndex);
    }
}
