package com.cloudtruss.employee;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class BigData {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    record Person(String firstName, String lastName, BigDecimal salary, String state, char gender, LocalDate birthDate, LocalTime birthTime) {
        long getAge() {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }

        String getDOBAsText() {
            return dateFormatter.format(birthDate);
        }

        String getBirthTimeAsText() {
            return timeFormatter.format(birthTime);
        }

        LocalDateTime getCompleteDOB() {
            return LocalDateTime.of(birthDate, birthTime);
        }
    }

    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis();
            Map<Boolean, Map<String, Long>> result;
                    // parallel() - splits the processes into different groups that are processed simultaneously, and it reduces processing time.
                    Files.lines(Path.of("/Users/courage/Desktop/JavaTraining/Employees/Hr5m.csv")).parallel()
                            .skip(1)
                            .limit(200)
                            .map(s -> s.split(","))
//                            .map(arr -> new Person(arr[2], arr[4], Long.parseLong(arr[25]), arr[32], arr[5].strip().charAt(0)))
                            .map(arr -> new Person(arr[2], arr[4], new BigDecimal(arr[25]), arr[32], arr[5].strip().charAt(0),
                                    LocalDate.parse(arr[10].strip(), dateFormatter),
                                    LocalTime.parse(arr[11].toLowerCase(), timeFormatter)))
                            .filter(p -> p.getAge() < 30)
                            .forEach(p -> System.out.printf("%s, %s %s - %d%n", p.lastName(), p.firstName(), p.getDOBAsText(), p.getAge()));

//                            .collect(groupingBy(Person::State, TreeMap::new, toList())); // groupBy, Map Type(used for ordering), what to do with the data.
                            //.filter(p -> p.gender == 'M') // filter out females and allow only data with men
//                            .collect(partitioningBy(p -> p.gender() == 'F', counting())); // Partitioning is a mix of filter() and groupingBy().
//                            .collect(partitioningBy(p -> p.gender() == 'F',
//                                    groupingBy(Person::state, counting())));
//                            .collect(
//                                    groupingBy(Person::state, TreeMap::new,
//                                            groupingBy(Person::gender,
//                                                    collectingAndThen(
//                                                            reducing(BigDecimal.ZERO, Person::salary, BigDecimal::add), // (a,b) -> a.add(b)
//                                                            //summingLong(Person::salary),
//                                                            NumberFormat.getCurrencyInstance()::format))
////                                    collectingAndThen(summingLong(Person::Salary), s -> String.format("£%,d.00%n", s))
////                                    collectingAndThen(summingLong(Person::salary), NumberFormat.getCurrencyInstance()::format) // s -> NumberFormat.getCurrencyInstance().format(s)
//                                    ));
//                            .forEach((state, salary) -> System.out.printf("%s -> %s%n", state, salary)); // printing a Map: (key-value) one line at time
//                            .collect(summingLong(Person::Salary));
//                            .map(arr -> arr[25])
//                            .mapToLong(Long::parseLong)
//                            .sum();
//                            .collect(summingLong(salary -> Long.parseLong(salary)));
            long stopTime = System.currentTimeMillis();
            //.forEach(System.out::println);
            //.count(); // count number of records
            //.collect(counting()); // Collect is a powerful framework for doing sophisticated things
            //System.out.println(result);
//            System.out.printf("£%,d.00%n", result);
            System.out.println(stopTime - startTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
