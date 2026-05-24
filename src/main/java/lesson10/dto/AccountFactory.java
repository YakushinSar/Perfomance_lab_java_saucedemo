package lesson10.dto;

import com.github.javafaker.Faker;

public class AccountFactory {

    //    использование Faker
    public static AccountLombok getAccountLombok(String type, String industry) {
        Faker faker = new Faker();
        return new AccountLombok(faker.company().name(), faker.phoneNumber().phoneNumber(), faker.phoneNumber().phoneNumber(), faker.internet().url(), faker.address().streetAddress(), type, industry);
    }
}
