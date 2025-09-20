package com.cloudtruss.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// JAVA only permits single inheritance. i.e.,
// A class cannot extend more than one one class.
// CEO can be composed of different classes
public class CEO extends Employee implements IEmployee, Flyer {
    private int averageStockPrice = 0;
    private Flyer flyer = new Pilot(1000, true);

    private final String ceoRegex = "\\w+=(?<avgStockPrice>\\d+)";
    private final Pattern ceoPat = Pattern.compile(ceoRegex);

    public CEO(String personText) {
        super(personText);
        Matcher ceoMat = ceoPat.matcher(peopleMatcher.group("details"));
        if (ceoMat.find()) {
            this.averageStockPrice = Integer.parseInt(ceoMat.group("avgStockPrice"));
        }
    }

    public int getSalary() {
        return 5000 * averageStockPrice;
    }

    public void fly() {
        flyer.fly();
    }

    public int getHoursFlown() {
        return flyer.getHoursFlown();
    }

    public void setHoursFlown(int hoursFlown) {
        flyer.setHoursFlown(hoursFlown);
    }

    public boolean isIfr() {
        return flyer.isIfr();
    }

    public void setIfr(boolean ifr) {
        flyer.setIfr(ifr);
    }
}
