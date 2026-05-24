package homework10.factory;

import com.github.javafaker.Faker;

public class CheckoutDataFactory {

    private static final Faker faker = new Faker();

    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    public static String getRandomPostalCode() {
        return faker.address().zipCode();
    }
}