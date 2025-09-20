package com.cloudtruss.employee;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;

public class LearnMapReducePattern {
    // The sum() is a reduced function

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
//        int result = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                //.skip(4)
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                .sum();
//        System.out.println(STR."Sum of Salaries: \{result}");

//        OptionalDouble average = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                .average();
//        double result = average.orElse(0);
//        System.out.println(STR."Average of Salaries: \{result}");
//    }

//        OptionalInt result = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                .min();
//        System.out.println(STR."Max Salaries: \{result.orElse(0)}");
//    }

//        long result = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                .count();
//        System.out.println(STR."Count: \{result}");
//    }

//    long result = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                //.reduce(0, (x, y) -> x + y); // identity is the number to start with that gives you back out what you put in for the first operation.
//                //.reduce(0, Integer::sum);
//                .reduce(10000000, Math::min);
//                //.reduce(0, (x, y) -> Math.max(x, y)); // identity is the number to start with that gives you back out what you put in for the first operation.
//        System.out.println(STR."result: \{result}");

//        OptionalInt reduce = peopleText
//                .lines()
//                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
//                .map(e -> (Employee) e)
//                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
//                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
//                .sorted(comparing(Employee::getLastName)
//                        .thenComparing(Employee::getFirstName)
//                        .thenComparing(Employee::getSalary))
//                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
//                .reduce((x, y) -> x + y); // reduce function without an identity will return an OptionalInt.
//        long result = (long) reduce.orElse(0);
//        System.out.println(STR."result: \{result}");

    OptionalInt reduce = peopleText
                .lines()
                .map(Employee::createEmployee) // map(s -> Employee.createEmployee(s)) Takes an input and returns a stream of IEmployee Objects
                .map(e -> (Employee) e)
                .filter(employeeEarningFiveK.and(employeeEarningBelowTwoHundredK))
                .collect(Collectors.toSet()).stream() // add .stream() to continue returning a stream instead of a Set.
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName)
                        .thenComparing(Employee::getSalary))
                .mapToInt(LearnStreams::showEmployeeAndGetSalary)
                .reduce(Integer::max); // anymethod that takes a lambda expression can also take a method reference
        long result = (long) reduce.orElse(0);
        System.out.println(STR."result: \{result}");

        Stream.of("tom", "mary", "sam", "brady")
                .reduce((a, b) -> a.toUpperCase().concat("_").concat(b).toUpperCase())
                .ifPresent(System.out::println);
    }
}
