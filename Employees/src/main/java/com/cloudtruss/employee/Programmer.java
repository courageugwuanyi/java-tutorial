package com.cloudtruss.employee;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee implements IEmployee, Chef {
    private int linesOfCode;
    private int yearsOfExp;
    private int iQ = 0;

    private final String progRegex = "\\w+=(?<locpd>\\d+),\\s*\\w+=(?<yeo>\\d{1,2}),\\s*\\w+=(?<iq>\\d{1,3})";
    private final Pattern progPat = Pattern.compile(progRegex);


    public Programmer(String personText) {
        super(personText);
        Matcher progMat = progPat.matcher(peopleMatcher.group("details"));
        if (progMat.find()) {
            this.linesOfCode = Integer.parseInt(progMat.group("locpd"));
            this.yearsOfExp = Integer.parseInt(progMat.group("yeo"));
            this.iQ = Integer.parseInt(progMat.group("iq"));
        }
    }

    public int getSalary() {
        return 3000 + this.yearsOfExp * this.iQ * this.linesOfCode;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public int getiQ() {
        return iQ;
    }

    public void setiQ(int iQ) {
        this.iQ = iQ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Programmer that)) return false;
        if (!super.equals(o)) return false;
        return iQ == that.iQ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), iQ);
    }
}
