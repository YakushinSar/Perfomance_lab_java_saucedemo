package lesson10;

public class FakerJava {

    /*
# Faker - генерация тестовых данных. Faker генерирует случайные, но НЕ гарантированно уникальные значения. Зачем нужен Faker:
✅ Не нужно придумывать тестовые данные вручную
✅ Данные выглядят реалистично
✅ Автоматически генерируются уникальные значения
# Где использовать	     Можно?	                         Почему
В тестах	        ✅ Рекомендуется	     Тесты отвечают за подготовку данных
В PageObject'ах	    ❌ Не рекомендуется	     PageObject не должен знать, откуда берутся данные
В Factory           ✅ Да                    Factory создаёт объекты с данными

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

String name = faker.company().name();              // случайное название компании
String phone = faker.phoneNumber().phoneNumber();  // случайный телефон
String url = faker.internet().url();               // случайный URL
String address = faker.address().streetAddress();  // случайный адрес
String email = faker.internet().emailAddress();    // случайный email
String sentence = faker.lorem().sentence();        // случайное предложение

# Пример в Factory:
public class AccountFactory {
    private static final Faker faker = new Faker();

    public static AccountDTO getRandomAccount() {
        return new AccountDTO(
                faker.company().name() + " " + System.currentTimeMillis(),
                faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().url(),
                faker.internet().emailAddress(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                getRandomType(),
                getRandomIndustry(),
                faker.lorem().sentence()
        );
    }

    private static String getRandomType() {
        String[] types = {"Customer", "Investor", "Partner"};
        return types[faker.random().nextInt(types.length)];
    }

    private static String getRandomIndustry() {
        String[] industries = {"Banking", "Technology", "Healthcare"};
        return industries[faker.random().nextInt(industries.length)];
    }
}
# Использование Factory в тесте:
@Test
public void testCreateAccount() {
    // Готовим данные через Factory (в тесте)
    AccountDTO account = AccountFactory.getRandomAccount();
    String uniqueName = account.getName();

    // Передаём в PageObject
    boolean isCreated = new LoginPage(driver, wait)
            .openLoginPage()
            .isPageOpened()
            .loginInLoginPage("will", "will")
            .isPageOpened()
            .goToAddAccountPage()
            .isPageOpened()
            .createAccount(account)
            .clickSave()
            .isPageOpened()
            .isAccountCreated(uniqueName);

    Assert.assertTrue(isCreated);
}


     */
}
