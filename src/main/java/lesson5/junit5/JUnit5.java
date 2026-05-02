package lesson5.junit5;

public class JUnit5 {
    /*
JUnit5 - делает все тоже самое что и TestNG.
# Зависимость в pom.xml (указана корректно)
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>6.0.3</version>
    <scope>test</scope>
</dependency>

# Аннотации:
@DisplayName - задать имя тесту или классу
@Disabled - отключить тест или класс тестов
@Tag - назначить тесту теги для группировки
@RepeatedTest - запустить тест несколько раз, аннотация @Test при использовании вместе не нужна
@Timeout - указать максимальное время выполнения теста
@Order - задает порядка выполнения тестовых методов в классе. Над классом надо указать @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ParameterizedTest - аннотация для параметризации, аннотация @Test при использовании вместе не нужна


# JUnit 5. Before и After
@BeforeEach & @AfterEach → перед/после каждого тестового метода
@BeforeAll & @AfterAll → один раз перед/после всех методов класса. в JUnit 5 по умолчанию методы @BeforeAll и @AfterAll
 должны быть обязательно static

# В JUnit 5 НЕТ аналогов для: IRetryAnalyzer (перезапуск упавших тестов) и ITestListener (слушатели жизненного цикла)
Альтернативы:
1. для Retry:
- @RepeatedTest(n) — повторяет тест, даже если он прошёл
- Сторонние библиотеки (например, junit-pioneer или плагин maven-surefire с настройкой rerunFailingTestsCount)
2. для TestListener:
- @BeforeEach / @AfterEach — для логирования
- через перезаписывание и имплементацию класса TestWatcher - аналог ITestListener в TestNG. После того, как тест выполнился,
 упал, был пропущен или прерван отображается:
testSuccessful() — тест завершился успешно.
testFailed() — тест упал (можно получить исключение через Throwable cause) .
testDisabled() — тест пропущен (например, из-за @Disabled) .
testAborted() — тест прерван.
Все эти методы получают объект ExtensionContext, из которого можно достать всю необходимую информацию о тесте: название
 (getDisplayName()), класс, метод и т.д.
Использование:
1. Создаем измененый класс CustomTestWatcher с оверрайдом методов:
public class CustomTestWatcher implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("✅ УСПЕХ: " + context.getDisplayName());
        // Здесь можно, например, очистить данные после успешного теста
    }
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("❌ ПАДЕНИЕ: " + context.getDisplayName());
        System.out.println("Причина: " + cause.getMessage());
        // Здесь можно сделать скриншот WebDriver'a или прикрепить лог
    }
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("⏩ ПРОПУЩЕН: " + context.getDisplayName() +
                           ". Причина: " + reason.orElse("не указана"));
    }
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("⚠️ ПРЕРВАН: " + context.getDisplayName());
    }
}
2. Над классом BaseTest делаем аннотацию @ExtendWith(CustomTestWatcher.class). Если нужно, чтобы TestWatcher работал только
 для конкретного тестового класса, то его надо ставить нед самим тестовым классом, а в BaseTest тогда не нужно.


# Soft Assertions в JUnit 5 реализуются через assertAll(), который позволяет выполнить все проверки и получить список всех
 ошибок в конце теста. Это удобно для проверки объектов с множеством полей
- assertAll() обязательно должен быть последним в тесте — после него проверки не имеет смысла писать, в конце собираются
 все ошибки и выводятся вместе
- Для одной логической проверки используйте обычные assertions
Синтаксис:
@Test
void softAssertionsTest() {
    assertAll("Проверка пользователя",
        () -> assertEquals(expectedName, actualName, "Имя не совпадает"),
        () -> assertEquals(expectedEmail, actualEmail, "Email не совпадает"),
        () -> assertEquals(expectedPhone, actualPhone, "Телефон не совпадает")
    );
}

     */
}
