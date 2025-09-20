package com.cloudtruss.employee;

public class ExceptionTests {
    public static void main(String[] args) {
        String[] array = {"1", "2", "3"};
        int num = 0;
        try {
            System.out.println(array.length / num);
            System.out.println(array[6]);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
