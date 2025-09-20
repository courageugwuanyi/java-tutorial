package datastore;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class OptionalTestTwo {

    record Car(String make, String model, String Color, Year year) {}
    record Person(Long id, String firstName, String lastName, Optional<Car> car) implements Repository.IDable<Long>, Repository.Savable {}
    public static void main(String[] args) {
        Repository<Person, Long> repo = new Repository<>();

        Person p1 = new Person(100L, "Tom", "Ben", Optional.of(new Car("Tesla", "X", "Red", Year.of(2018))));
        Person p2 = new Person(200L, "Jerry", "Mike", Optional.of(new Car("Tesla", "Y", "White", Year.of(2020))));
        Person p3 = new Person(200L, "Jake", "Johny", Optional.of(new Car("Tesla", "FSD", "Blue", Year.of(2019))));
        Person p4 = new Person(200L, "Mary", "Fink", Optional.of(new Car("Tesla", "3", "Black", Year.of(2021))));
        Person p5 = new Person(200L, "Smith", "Flinnestone", Optional.of(new Car("Tesla", "S", "Orange", Year.of(2023))));
        Person p6 = null;

        //Optional<Person> p11 = Optional.of(p1);
        Optional<Person> p11 = Optional.ofNullable(p6); // Using .ofNullable is similar to using  .filter(Optional::isPresent)
        p11.stream()
                .map(Person::firstName)
                .forEach(System.out::println);

        List<Optional<Person>> people = List.of(Optional.of(p1), Optional.of(p2), Optional.of(p3), Optional.of(p4), Optional.of(p5), Optional.ofNullable(p6));
        people.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Person::firstName)
                .forEach(System.out::println);

//        repo.save(p1);
//        repo.save(p2);
//
//        String fName = repo
//                .findById(100)
//                .map(Person::firstName).
//                orElse("Firstname not found");
//        System.out.println(fName);

//        Optional<Person> optPerson = Optional.ofNullable(p1);
//        System.out.println(optPerson
////                .map(Person::car)
//                .flatMap(Person::car)
//                .map(Car::make)
//                .orElse("Make Unknown"));
    }
}
