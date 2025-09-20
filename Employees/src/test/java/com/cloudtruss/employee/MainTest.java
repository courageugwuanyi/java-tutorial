package com.cloudtruss.employee;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testNameToSalary() {
        Main main = new Main();
        main.main(new String[0]);
        int salary = main.getSalary("Wilma");
        assertEquals(18000, salary);
    }
}