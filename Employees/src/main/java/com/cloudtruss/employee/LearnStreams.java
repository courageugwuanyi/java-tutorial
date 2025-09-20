package com.cloudtruss.employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

public class LearnStreams {

    private static String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=4000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=5000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=6000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=7000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=8000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Researcher, {locpd=9000, yeo=10, iq=140}
            Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300, yeo=14, iq=100}
            Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300, yeo=8, iq=105}
            Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630, yeo=3, iq=140}
            Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5, yeo=10, iq=100}
            Rubble, Barney, 2/2/1905, Manager, {orgSize=300, dr=10}
            Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100, dr=4}
            Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200, dr=2}
            Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500, dr=8}
            Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175, dr=20}
            Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
            Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
            Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
            Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
            Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
            Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
           """;


    public static void main(String[] args) {

        // Streams uses a method reference
        Predicate<String> otherEmployeesSelector = s -> s.contains("Researcher");
        Predicate<Employee> employeeEarningFiveK = e -> e.getSalary() > 5000;
        Predicate<Employee> employeeEarningBelowTwoHundredK = e -> e.getSalary() < 200000;
        int sum = peopleText
            .lines()
            //.filter(otherEmployeesSelector.negate()) // filtering using the methods in Predicate class
            .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
            .map(e -> (Employee) e)
            .filter(not(e -> ("N/A").equals(e.getLastName())) ) // continue if object's lastname is not equal to N/A
            .filter(not(e -> e instanceof Programmer)) // Only pass Objects that are not instances of Programmer class
            //.filter(e -> e instanceof Analyst) // Only pass Objects that are instances of Analyst class
            //.filter(e -> e.getSalary() > 5000 && e.getSalary() < 200000)
            .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
            //.distinct() // This gets rid of duplicates
            .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
            .sorted(comparing(Employee::getLastName)
                    .thenComparing(Employee::getFirstName)
                    .thenComparing(Employee::getSalary)
                    .reversed())
            .mapToInt(LearnStreams::showEmployeeAndGetSalary)
            .sum();
            //forEach(System.out::println); // loop .forEach(s -> System.out.println(s))- Terminal operation. Used only at the end of the pipe
        System.out.println(STR."Sum of Salaries: \{sum}");


        //collectorsToSet();
        // hascodeToHexString();
        //usingStreamWithClasses();
        //numericalStreamTypes();
        // convertIntStreamToStream(names);
        //readingTextFilewithStreams();
    }

    private static void collectorsToSet() {
        Set<Employee> collectedSet = peopleText
                .lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee) e)
                .collect(Collectors.toSet());
    }

    public static int showEmployeeAndGetSalary(Employee employee) {
        System.out.println(employee);
        return employee.getSalary();
    }

    private static void readingTextFilewithStreams() {
        try {
            Files.lines(Path.of("/Users/courage/Desktop/JavaTraining/Employees/src/main/java/com/cloudtruss/employee/employees.txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void usingStreamWithClasses() {

        record Car(String brand, String model, int year) {}
        Stream.of(new Car("Ford", "Bronco", 2012), new Car("Tesla", "ModelY", 2013))
            .filter(car -> "Ford".equals(car.brand))
            .forEach(System.out::println);
    }

    private static void numericalStreamTypes() {
        // Numerical Stream Types
        IntStream.range(1,4).forEach(System.out::println);
        IntStream.rangeClosed(1,4).forEach(System.out::println);
    }

    private static void convertIntStreamToStream() {
        String[] names = {null, "terry", "sam", "jake"};
        Stream.ofNullable(names).forEach(System.out::println);

        // convert Instream to Stream<Stings>
        IntStream.range(1,6)
                .mapToObj(String::valueOf)
                .map(s -> s.concat("-"))
                .forEach(System.out::print);

        System.out.println();
        IntStream.range(1,6)
                .boxed()
                .map(String::valueOf)
                .map(s -> s.concat("-"))
                .forEach(System.out::print);

        System.out.println();
        Arrays.stream(names)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }

    private static void hascodeToHexString() {
        //        List<String> nums = List.of("one", "two", "three", "four");
//        nums.stream()
        Stream.of("one", "two", "three", "four")
            .map(String::hashCode)
            .map(Integer::toHexString)
            .forEach(System.out::println);
    }
}
