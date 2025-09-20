package com.cloudtruss.peopledb.repository;

import com.cloudtruss.peopledb.model.Address;
import com.cloudtruss.peopledb.model.Region;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;


public class personRepositoryTest {

    private Connection connection;
    private personRepository repo;

    // It is okay to add Exceptions to the method signature of the test code. This is because we want to let a test method fail
    // The BeforeEach is run before each of the test is run
    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:~/peopleTest;TRACE_LEVEL_SYSTEM_OUT=0".replace("~", System.getProperty("user.home")));
        connection.setAutoCommit(false);
        repo = new personRepository(connection);
    }

    // Runs after each method is finished running
    // If an exception is thrown when a test method is run, the tearDown method will still get called by JUnit.
    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // The most efficient way is to use @BeforeALl to open connection once and @AfterAll to close connections after all tests has been run
    // To be explored later.


    @Test
    public void canSaveOnePerson()  {
        Person person = new Person("John", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6")));
        Person savedPerson = repo.save(person);
        assertThat(savedPerson.getId()).isGreaterThan(0);
    }

    @Test
    public void canSaveTwoPersons() {
        Person person = new Person("Emma", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Person person2 = new Person("John", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6")));
        Person savedPerson = repo.save(person);
        Person savedPerson2 = repo.save(person2);
        assertThat(savedPerson.getId()).isNotEqualTo(savedPerson2.getId());
    }


    @Test
    public void canSavePersonWithHomeAddress() throws SQLException {
        Person person = new Person("EmmaBro", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Address address = new Address(null, "123 Beale St.", "Apt. 1A", "Wala Wala", "WA", "90210", "United States", "Fulton County", Region.WEST);
        person.setHomeAddress(address);
        Person savedPerson = repo.save(person);
        assertThat(savedPerson.getHomeAddress().get().id()).isGreaterThan(0);
        //connection.commit();
    }

    @Test
    public void canSavePersonWithWorkAddress() throws SQLException {
        Person person = new Person("Korage", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Address address = new Address(null, "123 Beale St.", "Apt. 1A", "Wala Wala", "WA", "90210", "United States", "Fulton County", Region.WEST);
        person.setWorkAddress(address);
        Person savedPerson = repo.save(person);
        assertThat(savedPerson.getWorkAddress().get().id()).isGreaterThan(0);
    }

    @Test
    public void canFindPersonByIdWithHomeAddress() throws SQLException {
        Person person = new Person("EmmaBro", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Address address = new Address(null, "123 Beale St.", "Apt. 1A", "Wala Wala", "WA", "90210", "United States", "Fulton County", Region.WEST);
        person.setHomeAddress(address);
        Person savedPerson = repo.save(person);
        Person foundPerson = repo.findById(savedPerson.getId()).get();
        assertThat(savedPerson.getHomeAddress().get().id()).isGreaterThan(0);
        assertThat(foundPerson.getHomeAddress().get().state()).isEqualTo("WA");
        //connection.commit();
    }

    @Test
    public void canFindPersonByIdWithWorkAddress() throws SQLException {
        Person person = new Person("Korage-2", "Work", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Address address = new Address(null, "123 Beale St.", "Apt. 1A", "Wala Wala", "WA", "90210", "United States", "Fulton County", Region.WEST);
        person.setWorkAddress(address);
        Person savedPerson = repo.save(person);
        Person foundPerson = repo.findById(savedPerson.getId()).get();
        assertThat(savedPerson.getWorkAddress().get().id()).isGreaterThan(0);
        assertThat(foundPerson.getWorkAddress().get().state()).isEqualTo("WA");
        //connection.commit();
    }

    @Test
    public void canFindPersonByIdWithChildren() throws SQLException {
        Person person = new Person("John", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        person.addChild(new Person("Johnny", "Smith", ZonedDateTime.of(2008, 2, 2, 10, 50, 13, 0, ZoneId.of("-6"))));
        person.addChild(new Person("Sarah", "Smith", ZonedDateTime.of(2002, 10, 24, 5, 6, 30, 0, ZoneId.of("-6"))));
        person.addChild(new Person("Jenny", "Smith", ZonedDateTime.of(2010, 4, 15, 3, 34, 23, 0, ZoneId.of("-6"))));

        Person savedPerson = repo.save(person);
        Person foundPerson = repo.findById(savedPerson.getId()).get();

        Set<String> firstNamesOfChildren = foundPerson.getChildren().stream()
                .map(Person::getFirstName)
                .collect(toSet());
        assertThat(firstNamesOfChildren).contains("Johnny", "Sarah", "Jenny");
    }

    @Test
    public void canSavePersonWithChildren() throws SQLException {
        Person person = new Person("John", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        person.addChild(new Person("Johnny", "Smith", ZonedDateTime.of(2008, 2, 2, 10, 50, 13, 0, ZoneId.of("-6"))));
        person.addChild(new Person("Sarah", "Smith", ZonedDateTime.of(2002, 10, 24, 5, 6, 30, 0, ZoneId.of("-6"))));
        person.addChild(new Person("Jenny", "Smith", ZonedDateTime.of(2010, 4, 15, 3, 34, 23, 0, ZoneId.of("-6"))));

        Person savedPerson = repo.save(person);
        savedPerson.getChildren().stream()
                        .map(Person::getId)
                        .forEach(id -> assertThat(id).isGreaterThan(0));
        //connection.commit();
    }

    @Test
    public void canFindPersonById()  {
        Person savedPerson = repo.save(new Person("test", "jackson", ZonedDateTime.now()));
        Person foundPerson = repo.findById(savedPerson.getId()).get();
        assertThat(foundPerson).isEqualTo(savedPerson); // There is now an equals method in the Person class
    }

    @Test
    public void testPersonIdNotFound() {
        Optional<Person> foundPerson = repo.findById(-1L);
        assertThat(foundPerson).isEmpty();
    }

    @Test
    public void canGetCount() {
        long startCount = repo.count();
        repo.save(new Person("Emma", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        repo.save(new Person("John", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        long endCount = repo.count();
        assertThat(endCount).isEqualTo(startCount + 2);

    }

    @Test
    public void canDeletePerson()  {
        Person person = new Person("Emma", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8")));
        Person savedPerson = repo.save(person);
        long startCount = repo.count();
        repo.delete(savedPerson);
        long endCount = repo.count();
        assertThat(endCount).isEqualTo(startCount - 1);
    }

    @Test
    public void canDeleteMultiplePersons() {
        Person p1 = repo.save(new Person("Emma", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        Person p2 = repo.save(new Person("John", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        repo.delete(p1, p2);
    }

    @Test
    public void canFindAllPersons() {
        repo.save(new Person("Emma", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        repo.save(new Person("John", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        repo.save(new Person("Emma1", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        repo.save(new Person("John1", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        repo.save(new Person("Emma2", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        repo.save(new Person("John2", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        repo.save(new Person("Emma3", "Smith", ZonedDateTime.of(1990, 10, 5, 15, 12, 0, 0, ZoneId.of("-8"))));
        repo.save(new Person("John3", "Smith", ZonedDateTime.of(1980, 11, 15, 15, 15, 0, 0, ZoneId.of("-6"))));
        List<Person> persons = repo.findAll();
        assertThat(persons.size()).isGreaterThanOrEqualTo(10);
    }

    @Test
    public void canUpdatePerson() {
        Person savedPerson = repo.save(new Person("test-2", "Smith", ZonedDateTime.now()));

        Person p1 = repo.findById(savedPerson.getId()).get();
        savedPerson.setSalary(new BigDecimal("73000.34"));
        repo.update(savedPerson); // Update in the DB

        Person p2 = repo.findById(savedPerson.getId()).get();
        assertThat(p2.getSalary()).isNotEqualTo(p1.getSalary());

    }

    @Test
    public void experiment() {
        Person p1 = new Person(10L, null, null, null);
        Person p2 = new Person(20L, null, null, null);
        Person p3 = new Person(30L, null, null, null);
        Person p4 = new Person(40L, null, null, null);
        Person p5 = new Person(50L, null, null, null);
        Person p6 = new Person(60L, null, null, null);

        // DELETE FROM PERSON WHERE ID IN (10, 20, 30, 40, 50)
        Person[] persons = Arrays.asList(p1, p2, p3, p4, p5, p6).toArray(Person[]::new); // toArray(new Person[]{})

        String personIds = Arrays.stream(persons)
                .map(Person::getId)
                .map(id -> String.valueOf(id))
                .collect(joining(","));
        System.out.println(personIds);

    }

    @Test
    @Disabled
    public void loadData() throws IOException, SQLException {
        Files.lines(Path.of("/Users/courage/Desktop/JavaTraining/Employees/Hr5m.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(arr -> {
                    LocalDate dob = LocalDate.parse(arr[10], DateTimeFormatter.ofPattern("M/d/yyy"));
                    LocalTime timeOfBirth = LocalTime.parse(arr[11].toLowerCase(), DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    LocalDateTime dateTimeOfBirth = LocalDateTime.of(dob, timeOfBirth);
                    ZonedDateTime zonedDateTimeOfBirth = ZonedDateTime.of(dateTimeOfBirth, ZoneId.of("UTC"));
                    Person person = new Person(arr[2], arr[4], zonedDateTimeOfBirth);
                    person.setSalary(new BigDecimal(arr[25]));
                    person.setEmail(arr[6]);
                    return person;
                })
                .forEach(repo::save); // p -> repo.save(p)
        connection.commit();
    }
}
