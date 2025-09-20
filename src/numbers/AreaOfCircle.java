package numbers;

public class AreaOfCircle {
    /**
     * This method calculates the area of a circle
     * It uses the formula: area = PI * radius ^ 2
     * @param radius
     * @return area (double)
     */
    public static double calculateAreaOfCircle(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public static void main(String[] args) {
        System.out.println(calculateAreaOfCircle(3));
    }
}
