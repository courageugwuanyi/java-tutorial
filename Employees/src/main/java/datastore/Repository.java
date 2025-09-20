package datastore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Repository<T extends Repository.IDable<V> & Repository.Savable, V> {

    record Person(String firstName, String lastName, Long id) implements IDable<Long>, Savable {}

    // Flad: interface is that does not have any method that will be implemented is called a flag
    interface Savable {}
    interface IDable<U> {
        U id();
    }

    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

     T save(T record) {
        records.add(record);
        return record;
    }

    Optional<T> findById(long id) {
        return records.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    static <T, V> V encrypt(T data, Function<T, V> encryptor) {
        return encryptor.apply(data);
    }

    public static void main(String[] args) {

        Repository<Person, Long> pRepo = new Repository<>();
        pRepo.save(new Person("John", "Doe", 1L));
        pRepo.save(new Person("Mary", "Johnson", 2L));
        pRepo.save(new Person("Jerry", "Fink", 3L));

        System.out.println(pRepo.findAll());
        Optional<Person> foundPerson = pRepo.findById(2);
        System.out.println(foundPerson);

        System.out.println(Repository.<String, String>encrypt("Hello", m -> m.toUpperCase())); // String::toUpperCase
        System.out.println(Repository.<String, Integer>encrypt("World", String::hashCode));
    }
}
