package lesson5;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class AssertJ {

    @Test
    public void testWithAssertJ() {
        // Пример проверки строк
        String actualString = "AssertJ is powerful!";
        Assertions.assertThat(actualString)
                .isNotEmpty()
                .startsWith("AssertJ")
                .endsWith("powerful!")
                .contains("is")
                .doesNotContain("weak");

        // Пример проверки чисел
        int actualNumber = 42;
        Assertions.assertThat(actualNumber)
                .isPositive()
                .isGreaterThan(40)
                .isLessThanOrEqualTo(42);

        double actualDouble = 3.14159;
        Assertions.assertThat(actualDouble)
                .isCloseTo(3.14, Assertions.offset(0.01))
                .isNotZero();

        // Пример проверки объектов
        Object actualObject = "test";
        Assertions.assertThat(actualObject)
                .isNotNull()
                .isInstanceOf(String.class)
                .isEqualTo("test");

        // Пример проверки списков (коллекций)
        List<String> actualList = Arrays.asList("one", "two", "three");
        Assertions.assertThat(actualList)
                .isNotEmpty()
                .contains("two")
                .doesNotContain("four")
                .containsExactly("one", "two", "three")
                .containsExactlyInAnyOrder("three", "one", "two");

        // Пример проверки массивов
        int[] actualArray = {1, 2, 3, 4, 5};
        Assertions.assertThat(actualArray)
                .isNotEmpty()
                .contains(3, 5)
                .doesNotContain(6)
                .startsWith(1, 2)
                .endsWith(4, 5);

        // Пример проверки дат
        LocalDate actualDate = LocalDate.now();
        Assertions.assertThat(actualDate)
                .isToday()
                .isBeforeOrEqualTo(LocalDate.now())
                .isAfterOrEqualTo(LocalDate.of(2020, 1, 1));

        // Пример проверки исключений
        Assertions.assertThatThrownBy(() -> {
                    throw new IllegalArgumentException("Invalid argument");
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid argument")
                .hasMessageContaining("Invalid");

        // Пример проверки null
        Object nullObject = null;
        Assertions.assertThat(nullObject)
                .isNull();

        // Пример проверки булевых значений
        boolean isTrue = true;
        Assertions.assertThat(isTrue)
                .isTrue();

        boolean isFalse = false;
        Assertions.assertThat(isFalse)
                .isFalse();
    }
}