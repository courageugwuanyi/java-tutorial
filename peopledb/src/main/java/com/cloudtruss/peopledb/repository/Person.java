package com.cloudtruss.peopledb.repository;


import com.cloudtruss.peopledb.annotation.Id;
import com.cloudtruss.peopledb.model.Address;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class Person {
    @Id
    private Long id;

    private ZonedDateTime birthDate;
    private String firstName;
    private String lastName;
    private BigDecimal salary = BigDecimal.ZERO; // new BigDecimal("0")
    private String email;
    private Optional<Address> homeAddress = Optional.empty();
    private Optional<Address> workAddress = Optional.empty();
    private Set<Person> children = new HashSet<>();
    private Optional<Person> parent = Optional.empty();

    public Person(Long id, String firstName, String lastName, ZonedDateTime dob, BigDecimal salary) {
        this(id, firstName, lastName, dob);
        this.salary = salary;
    }

    public Person(Long id, String firstName, String lastName, ZonedDateTime birthDate) {
        this(firstName, lastName, birthDate);
        this.id = id;
    }

    public Person(String firstName, String lastName, ZonedDateTime birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }


    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSalary() {
        return salary;
    }
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id)  && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) &&
                Objects.equals(birthDate.withZoneSameInstant(ZoneId.of("+0")), person.birthDate.withZoneSameInstant(ZoneId.of("+0"))); // normalize comparison to compare DOB as if they are GMT-0
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, birthDate, firstName, lastName);
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = Optional.ofNullable(homeAddress);
    }

    public Optional<Address> getHomeAddress() {
        return homeAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = Optional.ofNullable(workAddress);
    }

    public Optional<Address> getWorkAddress() {
        return workAddress;
    }

    public void addChild(Person child) {
        children.add(child); // 1 to many relationship
        child.setParent(this); // associating a child back with its parent
    }

    public void setParent(Person parent) {
        this.parent = Optional.ofNullable(parent);
    }

    public Optional<Person> getParent() {
        return parent;
    }

    public Set<Person> getChildren() {
        return children;
    }
}
