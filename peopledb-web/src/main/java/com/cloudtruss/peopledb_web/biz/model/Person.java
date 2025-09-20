package com.cloudtruss.peopledb_web.biz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An entity class is a Java class that represents a table in a relational database.
 * It is a fundamental concept in Java Persistence API (JPA) and other ORM (Object-Relational Mapping)
 * frameworks like Hibernate. Each instance of an entity class corresponds to a row in the table,
 * and each field in the class corresponds to a column in the table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "First Name cannot be empty.")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty.")
    private String lastName;

    @Email(message = "Enter a valid email.")
    @NotEmpty(message = "Emial cannot be empty")
    private String email;

    @Past(message = "Date of birth must be in the past.")
    @NotNull(message = "Date of birth must be specified.")
    private LocalDate birthDate;

    @DecimalMin(value = "1000", message = "Salary must be at least £1000")
    @NotNull(message = "Salary must be specified.")
    private BigDecimal salary;

    private String photoFileName;

    public static Person parse(String csvLine) {
        String[] fields = csvLine.split(",\\s*"); // split by comma and also capture any additional space
        LocalDate birthDate = LocalDate.parse(fields[10], DateTimeFormatter.ofPattern("M/d/yyyy"));
        return new Person(null, fields[2], fields[4], fields[6], birthDate, new BigDecimal(fields[25]), null);
    }
}
