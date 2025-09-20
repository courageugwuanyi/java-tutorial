package com.cloudtruss.employee;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmployee {
    protected static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>[\\w\\s]+),\\s*\\{(?<details>.*)\\}";

    public static final Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected final Matcher peopleMatcher;
    protected String lastName;
    protected String firstName;
    protected LocalDate dateOfBirth;
    protected NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    protected Employee() {
        peopleMatcher = null;
        lastName = "N/A";
        firstName = "N/A";
        dateOfBirth = null;
    }

    protected Employee(String personText) {
        peopleMatcher = PEOPLE_PAT.matcher(personText);
        if (peopleMatcher.find()) {
            this.lastName = peopleMatcher.group("lastName");
            this.firstName = peopleMatcher.group("firstName");

            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/y");
            this.dateOfBirth = LocalDate.from(dtFormatter.parse(peopleMatcher.group("dob")));
        }
    }

    public static final Employee createEmployee(String employeeText) {
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        // Example of Local Class
//        class MyLocalClass extends Employee {
//            @Override
//            public int getSalary() {
//                return 50;
//            }
//        }
        if (peopleMat.find()) {
            return switch(peopleMat.group("role")) {
                case "Manager" -> new Manager(employeeText);
                case "Programmer" -> new Programmer(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "CEO" -> new CEO(employeeText);
                default -> new Employee() { // Anonymous class
                    @Override
                    public int getSalary() {
                        return 0;
                    }

                    @Override
                    public int compareTo(IEmployee o) {
                        return 0;
                    }
                };
            };
        } else {
            return new DummyEmployee(); // () -> 0; Lambda Class.
        }
    }

    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.10;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s - %s", lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }

    /**
     * The equals() and hashCode() methods needs to be considering the same properties so that the HashSet<>() can work properly.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(lastName, employee.lastName) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth);
                //&& Objects.equals(getClass(), employee.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dateOfBirth); //getClass()
    }

    // controls the filtering for TreeSet<>()
    @Override
    public int compareTo(IEmployee o) {
        Employee other = (Employee) o;
        return this.lastName.compareTo(other.lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Static Nested Class
    private static final class DummyEmployee extends Employee {
        @Override
        public int getSalary() {
            return 0;
        }

        @Override
        public int compareTo(IEmployee o) {
            return 0;
        }
    }
}
