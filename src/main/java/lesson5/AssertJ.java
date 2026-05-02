package lesson5;

public class AssertJ {
    /*
# AssertJ — для красивых и читаемых проверок (assertions), работает и с TestNG, и с JUnit 5. В отличие от встроенных assertEquals(),
 assertTrue() и т.д., AssertJ позволяет писать проверки в естественном языке, также поддерживает цепочки проверок и мягкие
 assertions (SoftAssertions).В отличие от встроенных ассертов, где если сравнить Argument Order, то он отличается: JUnit 5
 имеет порядок (expected, actual), а TestNG — (actual, expected) в AssertJ же унифицирует этот синтаксис, предлагая
 assertThat(actual).isEqualTo(expected) — один и тот же подход для обоих фреймворков.
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.27.7</version>
            <scope>test</scope>
        </dependency>

Основной принцип AssertJ: все проверки начинаются с assertThat(actual).
Общая структура:
assertThat(actual)           // что проверяем
    .какой_то_метод()        // что проверяем
    .еще_метод()             // дополнительная проверка (опционально)
    .isEqualTo(expected);    // с чем сравниваем

Сравнение:
        (стандарт)	                             AssertJ (читаемый)
assertEquals(expected, actual)	         assertThat(actual).isEqualTo(expected)
assertTrue(condition)	                 assertThat(condition).isTrue()
assertNotNull(object)	                 assertThat(object).isNotNull()
assertTrue(list.contains("item"))	     assertThat(list).contains("item")

# Примеры AssertJ:
// Строки
assertThat("Hello").isEqualTo("Hello");
assertThat("   World   ").trim().isEqualTo("World");
assertThat("Hello World").contains("Hello").doesNotContain("Goodbye");
// Числа
assertThat(5).isGreaterThan(3).isLessThan(10);
assertThat(0).isZero();
assertThat(100).isPositive();
// Булевы значения
assertThat(true).isTrue();
assertThat(false).isFalse();
// Объекты и коллекции
assertThat(list).isNotEmpty().hasSize(3).contains("item1", "item2");
assertThat(object).isNotNull();
assertThat(object).isInstanceOf(String.class);
// Мягкие проверки (SoftAssertions)
SoftAssertions.assertSoftly(softly -> {
    softly.assertThat(title).isEqualTo("Products");
    softly.assertThat(url).contains("inventory");
    softly.assertThat(list).hasSize(6);
});
// Сообщения при падении
assertThat(actual)
    .as("Проверка: поле должно быть не пустым")
    .isNotEmpty();
     */
}
