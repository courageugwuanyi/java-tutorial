package com.cloudtruss.employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class Main2 {
    private static Set<IEmployee> employees;
    private static Map<String, Integer> salaryMap;

    record Programmer(String lastName, String firstName, LocalDate dateOfBirth, int locpd, int yoe, int iq) {}
    record Manager(String lastName, String firstName, LocalDate dateOfBirth, int orgSize, int dr) {}
    record Analyst(String lastName, String firstName, LocalDate dateOfBirth, int projectCount) {}
    record CEO(String lastName, String firstName, LocalDate dateOfBirth, BigDecimal avgStockPrice) {}

    public static void main(String[] args) {
        //Object employee = null;
        //Object employee = new Programmer("Martin", "Terry", LocalDate.of(1974, 9, 23), 1000, 25, 145);
        Object employee = new Programmer("Martin", "Terry", LocalDate.of(1974, 9, 23), 25, 25, 145);

        switch (employee) {
            case null:
                System.out.println("You supplied nothing!");
                break;
                // Pattern Matching for Records Components
                // "Programmer(var l, var f, var d, var locpd, var yoe, var iq) when locpd < 50" is called Unnamed Pattern
                // the underscore means unnamed variable.
            case Programmer(_, var f, _, var locpd, _, _) when locpd < 50:
                System.out.println(STR."\{f}, you only write \{locpd} lines of code per day. You are better than this!!");
                break;
            case Programmer(var l, var f, var d, var locpd, var yoe, var iq) when locpd >= 50:
                System.out.println(STR."\{f}, you wrote a whopping \{locpd} lines of code per day. Way to go !! ");
                break;
            default:
                System.out.println("No matches found");
        }
    }

}
