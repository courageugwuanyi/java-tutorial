package com.cloudtruss.employee;

import java.time.LocalDate;

public record Weirdo_TWO(String lastname, String firstname, LocalDate dateOfBirth) implements IEmployee {
    // constructor that takes only firstname and lastname
    public Weirdo_TWO(String lastname, String firstname) {
        this(lastname, firstname, null);
    }

    public int getSalary() {
        return 100;
    }

    @Override
    public int compareTo(IEmployee o) {
        return 0;
    }
}
