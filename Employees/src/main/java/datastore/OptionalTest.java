package datastore;

import java.util.Optional;

public class OptionalTest {
    private static String passWordGenerator(){
        return "123password";
    }
    public static void main(String[] args) {
        String msg = "Hello World!";
        String msg2 = null;
        Optional<String> optMsg2 = Optional.ofNullable(msg2);
        Optional<String> optMsg = Optional.ofNullable(msg);

        // use the Optional.orElse instead of if... else
        String finalOutput = optMsg2.orElse("There was no message").toUpperCase();
        System.out.println(finalOutput);

        if (optMsg2.isPresent()) { // The optional isEmpty() does the opposite of isPresent()
            System.out.println(optMsg2.get().toUpperCase());
        } else System.out.println("There was no message");

        // Creating your own RuntimeException. Don't do this. ALways check Java Docs to find an
        // exception that matches your case. You write your own exception only if you couldn't find one in the java docs
//        System.out.println(optMsg2.orElseThrow(() -> new RuntimeException("There was no message")));


        System.out.println(optMsg2.orElseGet(OptionalTest::passWordGenerator));
        System.out.println(optMsg2.orElse(passWordGenerator()));

        System.out.println(optMsg2.or(() -> Optional.of("Nothing here")));
        System.out.println(optMsg.filter(s -> s.length() > 3).orElse("Invalid"));
    }
}
