package classes.objects.methods.model;

import java.time.LocalDate;

public class Turtle extends Pet {

    public Turtle(String name, LocalDate dob) {
        super(name, dob);
    }

    public void hide() {
        // these methods are accessed because it is in the same package as PET
        super.packageProtected();
        super.protectedMethod();
    }
}
