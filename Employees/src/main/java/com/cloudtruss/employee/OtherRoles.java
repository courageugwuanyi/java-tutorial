package com.cloudtruss.employee;

public class OtherRoles extends Employee {

    public OtherRoles(String personText) {
        super(personText);
    }

    @Override
    public int getSalary() {
        return 0;
    }
}
