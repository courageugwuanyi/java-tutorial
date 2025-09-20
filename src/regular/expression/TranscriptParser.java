package regular.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TranscriptParser {
    static String transcript = """
            Student Number: 1234598872			Grade:		11
            Birthdate:		01/02/2000			Gender:	M
            State ID:		8923827123
           
            Cumulative GPA (Weighted)		3.82
            Cumulative GPA (Unweighted)	3.46
            """;
//    private static String getTranscript() {
//        return transcript;
//    }
    public static void main(String[] args) {
        // Boilerplate for using the regex engine
        // .*? = This expression reduces the greediness of the asterisks
        // \\b = This is a boundary between a word and non-word character(e.g a character and a whitespace/tab)
        // The non-greedy operator is used to match anything that is repeated within the string
        String regex = """
                Student\\sNumber:\\s(?<studentNumber>\\d{10})\\b.*? # Matches student's number
                Grade:\\t+(?<studentGrade>\\d{1,2})\\b.*? # Matches student's grade
                Birthdate:\\t+(?<birthMonth>\\d{1,2})/(?<birthDay>\\d{1,2})/(?<birthYear>\\d{4})\\b.*? # Matches student's birthdate
                Gender:\\t+(?<gender>\\w+)\\b.*? # Matches student's birthdate
                State\\sID:\\t+(?<stateID>\\d+)\\b.*? # Matches student's state id
                Cumulative\\sGPA\\s\\(Weighted\\)\\t+(?<cgpaWeighted>\\d+\\.?\\d+)\\b.*? # Matches student's weighted cumulative GPA
                Cumulative\\sGPA\\s\\(Unweighted\\)\\t+(?<cgpaUnweighted>\\d+\\.?\\d+)\\n # Matches student's unweighted cumulattive GPA
                """;
        Pattern pat = Pattern.compile(regex, Pattern.DOTALL | Pattern.COMMENTS);
        Matcher mat = pat.matcher(transcript);
        if (mat.matches()) {
            System.out.println(mat.group("studentNumber"));
            System.out.println(mat.group("studentGrade"));
            System.out.printf("Birth Month: %s, Birth Day: %s, Birth Year: %s \n",
                    mat.group("birthMonth"), mat.group("birthDay"), mat.group("birthYear") );
            System.out.println(mat.group("gender"));
            System.out.println(mat.group("stateID"));
            System.out.println(mat.group("cgpaWeighted"));
            System.out.println(mat.group("cgpaUnweighted"));
        }
    }
}
