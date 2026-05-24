package lesson10;

public class Lombok {

    /*
# Lombok позволяет избавиться от многословности Java в большинстве случаев и перестать писать огромные простыни кода из
 гетеров, сеттеров, equals, hashcode и toString (да их обычно генерит IDE, но читать и менять все равно приходится программисту)
Для работы нужно добавит зависимость в pom проекта и скачать в идеи плагин lombok
 <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </dependency>

# Lombok работает на этапе компиляции — готовый код можно посмотреть в target/classes В рантайме
 аннотаций уже нет — они подставляют готовый код.
Где использовать Lombok:
✅ DTO — идеально подходит, так как DTO только хранят данные (поля + геттеры/сеттеры)
❌ PageObject — не рекомендуется, так как PageObject содержит логику (методы действий), а Lombok скрывает важные методы
 и снижает читаемость

Аннотации:
1.@Getter / @Setter – добавление Getter и Setter в класс
2.@ToString – добавление toString в класс
3.@EqualsAndHashCode – генерация equals и hashCode функций.
❌ Плохо — Lombok не учитывает поля родителя в equals/hashCode
@EqualsAndHashCode
public class Child extends Parent {
    // ...
}
✅ Хорошо — явно указываем, что включаем родительские поля
@EqualsAndHashCode(callSuper = true)
public class Child extends Parent {
    // ...
}
4.@NoArgsConstructor and @AllArgsConstructor – генерация всех типов конструкторов
5.@Builder – имплементация Builder Pattern
@Builder
@Data
public class Car {
    private String model;
    private int speed;
}
// Использование:
Car car = Car.builder()
    .model("BMW")
    .speed(220)
    .build();
6.@Log4j2 – добавление логгера без лишних проблем (нужна доп.зависимость)
7.@Data – глобальная аннотация, генерирует все сразу - генерация getter, setter, toString, equals и hashCode функций

     */


}
