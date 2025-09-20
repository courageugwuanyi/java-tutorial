package com.cloudtruss.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee implements IEmployee{
    private int orgSize = 0;
    private int directReports = 0;

    private final String mgrRegex = "\\w+=(?<orgSize>\\d+),\\s*\\w+=(?<dr>\\d{1,3})";
    private final Pattern mgrPat = Pattern.compile(mgrRegex);

    public Manager(String personText) {
        super(personText);
        Matcher mgrMat = mgrPat.matcher(peopleMatcher.group("details"));
        if (mgrMat.find()) {
            this.orgSize = Integer.parseInt(mgrMat.group("orgSize"));
            this.directReports = Integer.parseInt(mgrMat.group("dr"));
        }
    }

    public int getSalary() {
        return 3000 + orgSize * directReports;
    }

    public int getOrgSize() {
        return orgSize;
    }

    public void setOrgSize(int orgSize) {
        this.orgSize = orgSize;
    }

    public int getDirectReports() {
        return directReports;
    }

    public void setDirectReports(int directReports) {
        this.directReports = directReports;
    }
}
