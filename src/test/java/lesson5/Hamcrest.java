package lesson5;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest {

    @Test
    public void testWithHamcrest() {
        String actualString = "Hamcrest is flexible!";
        assertThat(actualString, not(emptyOrNullString()));
        assertThat(actualString, startsWith("Hamcrest"));
        assertThat(actualString, endsWith("flexible!"));
        assertThat(actualString, containsString("is"));
        assertThat(actualString, not(containsString("weak")));

        // Пример проверки чисел
        int actualNumber = 42;
        assertThat(actualNumber, greaterThan(40));
        assertThat(actualNumber, lessThanOrEqualTo(42));
        assertThat(actualNumber, is(42));

        double actualDouble = 3.14159;
        assertThat(actualDouble, closeTo(3.14, 0.01));
        assertThat(actualDouble, not(equalTo(0.0)));

        // Пример проверки объектов
        Object actualObject = "test";
        assertThat(actualObject, notNullValue());
        assertThat(actualObject, instanceOf(String.class));
        assertThat(actualObject, is("test"));

        // Пример проверки списков (коллекций)
        List<String> actualList = Arrays.asList("one", "two", "three");
        assertThat(actualList, not(empty()));
        assertThat(actualList, hasItem("two"));
        assertThat(actualList, not(hasItem("four")));
        assertThat(actualList, contains("one", "two", "three"));
        assertThat(actualList, containsInAnyOrder("three", "one", "two"));

        // Пример проверки дат
        LocalDate actualDate = LocalDate.now();
        assertThat(actualDate, notNullValue());
        assertThat(actualDate.isAfter(LocalDate.of(2020, 1, 1)), is(true));
        assertThat(actualDate.isBefore(LocalDate.now().plusDays(1)), is(true));

        // Пример проверки исключений
        try {
            throw new IllegalArgumentException("Invalid argument");
        } catch (Exception e) {
            assertThat(e, instanceOf(IllegalArgumentException.class));
            assertThat(e.getMessage(), containsString("Invalid"));
        }

        // Пример проверки null
        Object nullObject = null;
        assertThat(nullObject, nullValue());

        // Пример проверки булевых значений
        boolean isTrue = true;
        assertThat(isTrue, is(true));

        boolean isFalse = false;
        assertThat(isFalse, is(false));
    }
}
