package com.cloudtruss.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee implements IEmployee {
    private int projectCount = 0;

    private final String analystRegex = "\\w+=(?<projectCount>\\d+)";
    private final Pattern analystPat = Pattern.compile(analystRegex);

    public Analyst(String personText) {
        super(personText);
        Matcher analystMat = analystPat.matcher(peopleMatcher.group("details"));
        if (analystMat.find()) {
            this.projectCount = Integer.parseInt(analystMat.group("projectCount"));
        }
    }

    public int getSalary() {
        return 3000 * 2 * projectCount;
    }
}
