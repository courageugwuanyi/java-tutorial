import classes.objects.methods.model.Address;
import classes.objects.methods.model.Person;

import java.time.LocalDate;

public class HelloWorld {
    public static void main(String[] args) {

        Address tomAddress = new Address("Mexico", "China", "India", null);
        Address janeAddress = new Address("Mexico", "", null, null);

        // An instance (or object of a Person Class)
        // initialize this object with the data
        Person tom = new Person("Tom", "smith", LocalDate.of(1984, 6, 15), tomAddress);
        Person jane = new Person("Janet", "Jackson", LocalDate.of(1990, 10, 8), janeAddress);

        // amy does not have any data in place.
        Person amy = new Person();
        tom.setSpouse(jane);

        tom.setFirstName("tommy");
        System.out.format("The person's first name is %s and last name is %s", tom.getFirstName(), tom.getLastName());
    }
}
