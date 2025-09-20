public class LearnStrings {

    public static String split(String text) {
        if (text.isEmpty() || text.isBlank()) {
            return "You did not enter a string";
        }
        return text.strip();
    }

    public static void main(String[] args) {
        // reference to the memory location where apple resides in memory
        String fruit = "apple";

        // apple is already created in memory, thus no new memory is created for apple,
        // therefore anotherFruit will be pointing to the same memory address of apple
        String anothorFruit = "apple";

        // Since we use the new String() here, a new space is always created in memory for the vegetables each time
        String vegetable = new String("Brocolli");
        String anotherVegetable = new String("Brocolli");

        System.out.println(fruit == anothorFruit);
        System.out.println(vegetable == anotherVegetable);

        System.out.println();
        String myText = "abcdef";
        String myText2 = "ABCDEF";
        System.out.println(myText.toUpperCase());
        System.out.println(myText2.toLowerCase());

        // Popular character code schemes: unicode
        // unicode upper and lowercase
        // a space is also a character that has a unicode behind it.
        // if there is a whitespace and no character, then it is blank
        // if there is space it means it is not empty.
        System.out.println();
        String text = " ";
        System.out.println(text.isBlank());
        System.out.println(text.isEmpty());

        // replace() is case sensitive
        System.out.println();
        String someText = "Here is my nice text";
        System.out.println(someText.replace("nice", "awesome"));
        System.out.println(someText.replace("i", "I"));

        // strip() and trim() - remove whitespace from strings
        // strip() is much more aware and sensitive to character encodings.
        // always choose strip()
        System.out.println();
        String firstName = " Jake ";
        String otherText = """
                         first line here
                            second line here
                third line here
                    ...
                """;
        System.out.format("'%s'\n", firstName.strip());
        System.out.println(firstName.trim());
        System.out.format("'%s'\n", firstName.stripLeading());
        System.out.format("'%s'\n", firstName.stripTrailing());
        System.out.format("%s\n", otherText.stripIndent());

        System.out.println(split(" Jake "));

    }
}
