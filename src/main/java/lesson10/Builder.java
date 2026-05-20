package lesson10;

public class Builder {

    /*
# Builder Pattern
Builder помогает создавать сложные объекты пошагово, особенно когда у объекта много полей. Когда НЕ использовать: У объекта 1-2 поля
 (проще через конструктор)

## Как реализовать с Lombok:
@Getter
@Builder
@AllArgsConstructor
public class AccountLombok {
    private String name;
    private String phone;
    private String fax;
    private String website;
    private String street;
    private String type;
    private String industry;
}

Все аннотации обязательны:
@Builder — генерирует билдер
@AllArgsConstructor — нужен для @Builder
@Getter — для доступа к полям

# Сравнение создания объекта:
❌ Через конструктор (7 параметров — легко ошибиться, непонятно, какой параметр за что отвечает)
Account account = new Account("test", "+123456", "+1234567", "avito.ru",
    "test street", "Investor", "Banking");

✅ Через Builder (каждый параметр подписан)
AccountLombok account = AccountLombok.builder()
    .name("test")
    .phone("+123456")
    .fax("+1234567")
    .website("avito.ru")
    .street("test street")
    .type("Investor")
    .industry("Banking")
    .build();

✅ Можно создать пустой объект (все поля null)
AccountLombok emptyAccount = AccountLombok.builder().build();

✅ Можно заполнить только нужные поля
AccountLombok partialAccount = AccountLombok.builder()
    .name("test")
    .type("Investor")
    .build();

@Builder.Default — это аннотация Lombok, ставит значение по умолчанию, если поле не заполнили в Builder.
@Builder
public class User {
    @Builder.Default
    private boolean active = true;  // по умолчанию активен
}

User user = User.builder().build();     // active = true
User user2 = User.builder().active(false).build();  // active = false


     */
}