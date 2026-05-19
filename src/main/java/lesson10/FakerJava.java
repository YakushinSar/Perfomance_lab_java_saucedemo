package lesson10;

public class FakerJava {

    /*
# Faker - генерация тестовых данных
Зачем нужен Faker:
✅ Не нужно придумывать тестовые данные вручную
✅ Данные выглядят реалистично
✅ Автоматически генерируются уникальные значения

Faker позволяет генерировать реалистичные тестовые данные (имена, телефоны, адреса, URL и т.д.).
# Подключение зависимости:
<!-- https://mvnrepository.com/artifact/com.github.javafaker/javafaker -->
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>

# Использование:
Faker faker = new Faker();
String name = faker.company().name();           // случайное название компании
String phone = faker.phoneNumber().phoneNumber(); // случайный телефон
String url = faker.internet().url();            // случайный URL
String address = faker.address().streetAddress(); // случайный адрес

# Пример в Factory:
public static AccountLombok getAccountLombok(String type, String industry) {
    Faker faker = new Faker();
    return AccountLombok.builder()
        .name(faker.company().name())
        .phone(faker.phoneNumber().phoneNumber())
        .fax(faker.phoneNumber().phoneNumber())
        .website(faker.internet().url())
        .street(faker.address().streetAddress())
        .type(type)
        .industry(industry)
        .build();
}

     */
}
