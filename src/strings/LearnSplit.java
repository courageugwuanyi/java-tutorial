package strings;

import java.util.Arrays;

public class LearnSplit {
    public static void main(String[] args) {
        String myText = """
                Smith,Fred,1/1/79,111 ABC St.,Apple,CA
                McGuire,Jery,2/2/80,2222 ADEF St.,Orange,NV
                Flintstone,Fred,3/3/81,3333 GHI St.,Pear,MO
                Rubble,Barney,4/4/82,444 JKL St.,Pineapple,IL
                Jetson,George,5/5/83,5555 MNO St.,Grapefruit,NY
                """;

        // Count how many records they are
        String[] recordsOfPeople = myText.split("\n");
        String personRecord = recordsOfPeople[3];
        System.out.println("Total Number of Records:" + recordsOfPeople.length);
        String[] rubble = personRecord.split(",", 5);
        System.out.println(Arrays.toString(Arrays.copyOfRange(rubble, 1, 4)));


        // startsWith() and endsWith(),
        // strip() is used to trim any leading or trailing spaces
        String filename = "my-file.txt";
        System.out.println(filename + " ends with txt?: " + filename.endsWith("txt"));
    }
}
