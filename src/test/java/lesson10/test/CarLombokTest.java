package lesson10.test;


import lesson10.dto.CarLombok;
import org.testng.annotations.Test;

public class CarLombokTest {

    @Test
    public void test() {
        CarLombok car = new CarLombok("BMW", 220);
        CarLombok car1 = new CarLombok("BMW", 220);
        CarLombok car2 = new CarLombok();

        System.out.println(car);
        System.out.println(car.equals(car1));


    }
}
