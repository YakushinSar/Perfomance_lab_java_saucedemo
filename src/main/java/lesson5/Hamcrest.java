package lesson5;

public class Hamcrest {
    /*
# Hamcrest — библиотека для гибких проверок с матчерами. Матчер — это условие, которое проверяет значение.
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest</artifactId>
    <version>2.2</version>
    <scope>test</scope>
</dependency>

Особенности:
- Проверки строятся через комбинацию матчеров (is(), not(), containsString() и т.д.)
- Можно комбинировать матчеры: allOf(), anyOf(), not()
- Позволяет создавать свои матчеры

Пример:
assertThat(actualString, startsWith("Hamcrest"));
assertThat(actualList, hasItem("two"));
assertThat(actualNumber, allOf(greaterThan(40), lessThan(100)));
     */
}
