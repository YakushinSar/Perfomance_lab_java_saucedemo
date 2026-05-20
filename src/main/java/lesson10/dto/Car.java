package lesson10.dto;

import java.util.Objects;

public class Car {

    String model;
    int speed;
    // при добавлении нового поля приходится переписывать конструктор и переопределенные методы, чтобы так не делать можно использовать lombok
    String enjine;

    public Car(String model, int speed) {
        this.model = model;
        this.speed = speed;
    }

    // переопределяем метод toString для вывода не ссылок объекта а содержимого
    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", speed=" + speed +
                '}';
    }

    //  переопеределяем методы equals и hashCode, чтобы сравнивать объекты не по ссылкам, а по содеожимому
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return speed == car.speed && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, speed);
    }
}
