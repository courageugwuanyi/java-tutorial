package com.cloudtruss.employee;

import java.util.Arrays;

public class LearnFlatteningStreams {
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


    public static void main(String[] args) {

        peopleText
                .lines()
                .map(Employee::createEmployee)
                .map(e -> (Employee) e)
                .map(Employee::getFirstName)
                .map(firstName -> firstName.split(""))
                .flatMap(Arrays::stream)
                .map(String::toLowerCase)
                .distinct()
                .forEach(System.out::print);
    }

}
