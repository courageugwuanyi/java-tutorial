package com.cloudtruss.employee;

import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;

public class Main {
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

    private static Set<IEmployee> employees;
    private static Map<String, Integer> employeesMap;


    public static void main(String[] args) {
        Matcher peopleMat = Employee.PEOPLE_PAT.matcher(peopleText);

        // Inheritance should not go more than 2 levels deep
        // Composition is used when you did not write the class you want to inherit
        // and you don't specifically know if that class is meant to be extended
        // In situations where frameworks write classes that are meant to be extended then composition is the best
        // when is it best to use inheritance vs composition.

        /** CEO flyer = new CEO("");
        flyer.getHoursFlown();
        String name = flyer.firstName;

        Programmer coder = new Programmer("");
        coder.cook("Pizza"); */

        int totalSalaries = 0;
        IEmployee employee = null;

        // Filter  with the lastname of compareTo() in Employee Class
        //employees = new TreeSet<>();

        // Filter with both with the lastname of compareTo() in Employee Class
        // and firstName based on the Comparator supplied to the TreeSet.
        employees = new TreeSet<>((o1, o2) -> {
            Employee e1 = (Employee) o1; Employee e2 = (Employee) o2;
            return e1.firstName.compareTo(e2.firstName);
        });

        employeesMap = new LinkedHashMap<>();

        while (peopleMat.find()) {
            // Factory Method
            employee = Employee.createEmployee(peopleMat.group());
            Employee emp = (Employee) employee;
            employees.add(employee);
            employeesMap.put(emp.firstName, emp.getSalary());
        }

        for (IEmployee worker : employees) {
            totalSalaries += employee.getSalary();
            System.out.println(worker);
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("%nThe total salaries is %s%n", currencyInstance.format(totalSalaries));
        System.out.println(employees.size());
        System.out.println(employeesMap.containsKey("Barney"));
        System.out.println(employeesMap.keySet());
        System.out.println(employeesMap.values());
        System.out.println(employeesMap.entrySet());
        System.out.println(employeesMap.hashCode());
        System.out.println(employeesMap.remove("Fred"));

//        for (Map.Entry<String, Integer> entry : employeesMap.entrySet()) {
//            System.out.printf("Key= %s, Value= %s%n", entry.getKey(), entry.getValue());
//        }
    }

    public int getSalary(String firstName) {
        return employeesMap.getOrDefault(firstName, -1);
//        for (IEmployee employee : employees) {
//            Employee emp = (Employee) employee;
//            if (Objects.equals(emp.firstName, firstName)) {
//                return emp.getSalary();
//            }
//        }
//        return 0;
    }
}