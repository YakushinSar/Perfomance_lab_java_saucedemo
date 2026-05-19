package lesson10.test;

import lesson10.dto.Car;
import org.testng.annotations.Test;

public class CarTest {

    @Test
    public void test() {
        Car car = new Car("BMW", 220);
        Car car1 = new Car("BMW", 220);
        // переопределяем метод toString для вывода не ссылок объекта а содержимого
        System.out.println(car);
        //  переопеределяем методы equals и hashCode, чтобы сравнивать объекты не по ссылкам, а по содеожимому
        System.out.println(car.equals(car1));


    }
}
