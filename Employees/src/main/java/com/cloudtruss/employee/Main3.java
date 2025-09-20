package com.cloudtruss.employee;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;

public class Main3 {
    private static String peopleText = """
            Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000, yeo=10, iq=140}
            Flinstone, Fred, 1/1/1900, Researcher, {locpd=2000, yeo=10, iq=140}
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
        List<IEmployee> employees = new ArrayList<>();

        while (peopleMat.find()) {
            // Factory Method
            employee = Employee.createEmployee(peopleMat.group());
            employees.add(employee);

            // compareObjectTypes(employee);
        }

        IEmployee myEmp = employees.get(5);
        System.out.println(employees.contains(myEmp));
        System.out.println(myEmp);

        IEmployee employee1 = Employee.createEmployee("Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5, yeo=10, iq=100}");
        System.out.println(employees.contains(employee1));

        // This will return false because the equals method only checks if prog1 and prog2 are pointing to
        // the same object address in memory
        // If we want to compare the object itself rather the reference to the memory where the object sits,
        // Then we need to compare some required or important fields in the object that are least likely fields to change.
        System.out.println(myEmp.equals(employee1)); // programmer1 == programmer2
        System.out.println(employee1);

        // to use the comparator.naturalOrder on employees class, you need to extend and
        // implement the Comparable Interface in the Employee class.
        Collections.sort(employees, Comparator.reverseOrder());

        //Collections.shuffle(employees);

        //sortListInPlace(employees);

        //removeElementsFromList(employees);

        // A slice of a list
        List<IEmployee> subListOfEmployees = employees.subList(1, 4);
        System.out.println(subListOfEmployees);

        // Convert List to Arrays
        IEmployee[] otherArray = employees.toArray(new IEmployee[0]);
        System.out.println(otherArray);

        List<String> removeEmployee = createUndesirableList();
        //removeUndesirables(employees, removeEmployee);

        List<String> newStrings = new ArrayList<>(16);
        newStrings.addAll(removeEmployee);
        System.out.println(newStrings);

        for (IEmployee worker : employees) {
            totalSalaries += employee.getSalary();
            System.out.println(worker);
        }



        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.printf("%nThe total salaries is %s%n", currencyInstance.format(totalSalaries));

        // Showing example of RECORDS, a new data type in JAVA. A minimalistic class.
        Weirdo_TWO larry = new Weirdo_TWO("David", "Larry", LocalDate.of(1999, 04, 24));
        Weirdo_TWO ben = new Weirdo_TWO("Luke", "Ben");
        System.out.println(larry.lastname());
        System.out.print(ben.firstname());
        System.out.print(STR." \{ben.getSalary()}");

    }

    private static void sortListInPlace(List<IEmployee> employees) {
        // Sorts the List in-place.
        employees.sort((o1, o2) -> {
            // Pattern Matching
            if (o1 instanceof Employee emp1 && o2 instanceof Employee emp2) {
                // compareTo() returns 0 or -1 or 1
                int lnameResult = emp2.lastName.compareTo(emp1.lastName);
                return lnameResult != 0 ? lnameResult : Integer.compare(emp1.getSalary(), emp2.getSalary());
            }
            return 0;
        });
    }

    private static void removeElementsFromList(List<IEmployee> employees) {
        // Removing Elements from a List.
//        IEmployee first = employees.get(0);
//        IEmployee second = employees.get(1);
//        IEmployee third = employees.get(2);
//        employees.remove(first);
//        employees.remove(second);
//        employees.remove(third);

        // This is a much better way to remove elements from a List/
        employees.remove(0);
        employees.remove(1);
        employees.remove(2);
    }

    private static List<String> createUndesirableList() {
        // of() are immutable list. Once set nothing can be removed or added to the list
        // if you want to make it mutable you can pass it in a new ArrayList..
        List<String> removeEmployee = List.of("Wilma5", "Barney4", "Fred2");
//        removeEmployee.add("Wilma5");
//        removeEmployee.add("Barney4");
//        removeEmployee.add("Fred2");
        ArrayList<String> removeEmployees = new ArrayList<>(List.of("Wilma5", "Barney4", "Fred2"));
        removeEmployees.sort(Comparator.naturalOrder());
        String firstItem = removeEmployee.getFirst();
        return removeEmployees;
    }

    private static void removeUndesirables(List<IEmployee> employees, List<String> removeEmployee) {
        // Looping with iterator to remove an item from a collection while you are iterating over that same collection
        for (Iterator<IEmployee> it = employees.iterator(); it.hasNext();) {
            IEmployee worker = it.next();
            // worker is compared to employee
            // worker is also cast as an Employee Type and named tmpWorker
            // e.g., Employee tmpWorker = (Employee) worker.
            // This is referred to as pattern Matching
            if (worker instanceof Employee tmpWorker) {
                if(removeEmployee.contains(tmpWorker.firstName)) {
                    it.remove();
                }
            }
        }
    }

    private static void compareObjectTypes(IEmployee employee) {
        // employee.getClass().equals(Programmer.class);
        if ( employee instanceof Programmer) {
            // old school way of casting during object comparison
            Programmer prog = (Programmer) employee;
            System.out.println((prog.getiQ()));
        } else if (employee instanceof Manager) {
            // shorthand of old school way of object comparison
            System.out.println(((Manager) employee).getOrgSize());
        } else if (employee instanceof Analyst analyst) {
            // this pattern of matching is available only available in Java16
            System.out.println(analyst.getSalary());
        }else if (employee instanceof CEO ceo) {
            System.out.println(ceo.getBonus());
        } else {
            System.out.println("Default output");
        }
    }
}
