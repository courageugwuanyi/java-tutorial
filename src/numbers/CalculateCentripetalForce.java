package numbers;

import java.util.Scanner;

public class CalculateCentripetalForce {

    private static double calculatePathVelocity(double radius, double period) {
        double circumferenceOfCircle = 2 * (Math.PI * radius);
        return circumferenceOfCircle / period;
    }

    private static double calculateCentripetalAcceleration(double pathVelocity, double radius) {
        return Math.pow(pathVelocity, 2) / radius;
    }

    private static double calculateCentripetalForce(double mass, double centripetalAcceleration) {
        return mass * centripetalAcceleration;
    }

    public static double calculateCentripetalForce(double mass, double radius, double period) {
        double pathVelocity = calculatePathVelocity(radius, period);
        double centripetalAcceleration = calculateCentripetalAcceleration(pathVelocity, radius);

        return calculateCentripetalForce(centripetalAcceleration, mass);
    }

    public static void main(String[] args) {
        System.out.println(calculateCentripetalForce(0.2, 0.8, 3));
        System.out.println(calculateCentripetalForce(0.3, 5));
    }
}
