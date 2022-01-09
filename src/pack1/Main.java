package pack1;


public class Main {

    public static void main(String[] args) {

        long timeBefore = System.currentTimeMillis();
        Car car = Car.createCar();
        long timeAfter = System.currentTimeMillis();
        System.out.println("The car is created after " + ((timeAfter - timeBefore) / 1000) + " seconds");
    }
}
