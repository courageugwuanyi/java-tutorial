package com.cloudtruss.employee;

import java.time.LocalDate;
import java.util.Objects;

public class Weirdo {
    private String lastName;
    private String firstName;
    private LocalDate dateOfBirth;

    public Weirdo(String lastName, String firstName, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weirdo weirdo = (Weirdo) o;
        return Objects.equals(lastName, weirdo.lastName) && Objects.equals(firstName, weirdo.firstName) && Objects.equals(dateOfBirth, weirdo.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, dateOfBirth);
    }
}
