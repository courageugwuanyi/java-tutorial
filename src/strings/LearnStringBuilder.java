package strings;

import static java.lang.StringTemplate.STR;

public class LearnStringBuilder {
    public static void main(String[] args) {
        String firstWord = "Apple";
        String secondWord = "Banana";
        String thirdWord = "orange";
        System.out.println(thirdWord.compareTo(firstWord));
        System.out.println(thirdWord.compareToIgnoreCase(firstWord));

        String myText = "Four score and seven years ago";
        System.out.println(myText.contains("score"));

        String text1 = "this is my text1 and ";
        String text2 = "this is my text2";
        System.out.println(text1.concat(text2));

        // String Builder is used to build long strings. Use String Builder rather than concat
        String finalString = new StringBuilder(text1.length() + text2.length() + 1)
                .append("Line 17: ")
                .append(text1)
                .append(text2)
                .toString();
        System.out.println(finalString);

        // String Buffer is older than the String Builder.
        // String Buffer is thread safe and multiple buffer will be able to run at the sametime
        // Thread Safety comes at the expense of performance and slower.
        // Use the String Builder always rather than String Buffer.
        String otherFinalString = new StringBuffer()
                .append("Line 28: ")
                .append(text1)
                .append(text2)
                .toString();
        System.out.println(finalString);

//        System.out.format("Line 35: %s %s \n", text1, text2);

        // preview of using StringTemplate
        System.out.format(STR."Line 35: \{text1} \{text2} \n");

        String oneMoreFinalString = String.format("Line 37: %s %s ", text1, text2);
        System.out.println(oneMoreFinalString);

        String mySimpleText = "four";
        System.out.println(mySimpleText.length());
    }
}
