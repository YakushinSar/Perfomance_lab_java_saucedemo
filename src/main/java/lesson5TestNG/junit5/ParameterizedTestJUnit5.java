package lesson5TestNG.junit5;

public class ParameterizedTestJUnit5 {

    /*
@ParameterizedTest - аннотация для параметризации, аннотация @Test при использовании вместе не нужна. Необходимо добавить
 библиотеку в pom файл
        <!-- junit-jupiter-params - для параметризированных тестов на junit -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>6.0.3</version>
            <scope>test</scope>
        </dependency>

# Обязательно указывается источник данных через одну из аннотаций, плюс в том что все находится в пределах одного тестового метода:
1. @ValueSource - Один параметр (массив значений), @ValueSource(strings = {"standard_user", "locked_out_user", "problem_user"})
2. @CsvSource - Несколько параметров через запятую
@CsvSource({
    "standard_user, secret_sauce",
    "problem_user, secret_sauce",
    "performance_glitch_user, secret_sauce"
})
3. @CsvFileSource - Данные из CSV-файла, @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
4. @MethodSource - Данные из метода-фабрики, @MethodSource("loginDataProvider")
static Stream<Arguments> loginDataProvider() {
    return Stream.of(
        Arguments.of("standard_user", "secret_sauce", ""),
        Arguments.of("locked_out_user", "secret_sauce", "Sorry"),
        Arguments.of("", "secret_sauce", "Username is required"),
        Arguments.of("standard_user", "", "Password is required")
    );
}
5. @EnumSource - Передача enum-констант,@EnumSource(UserType.class)
public enum UserType {
    STANDARD("standard_user", "secret_sauce", true),
    LOCKED("locked_out_user", "secret_sauce", false),
    PROBLEM("problem_user", "secret_sauce", true);

    public final String username;
    public final String password;
    public final boolean shouldPass;

    UserType(String username, String password, boolean shouldPass) {
        this.username = username;
        this.password = password;
        this.shouldPass = shouldPass;
    }
}

# Пример
@ParameterizedTest
@CsvSource({
    "Teach, 10",
    "Me, 5",
    "Skills, 15"
})
void testFruits(String school, int quantity) {
    System.out.println(school + " количество: " + quantity);
}
     */
}
