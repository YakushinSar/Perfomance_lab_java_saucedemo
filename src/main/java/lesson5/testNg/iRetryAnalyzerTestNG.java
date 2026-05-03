package lesson5.testNg;

public class iRetryAnalyzerTestNG {
    /*
1. Имплементируем интерфейс iRetryAnalyzer
2. Имплементируем метод retry как нам необходимо
3. Используем объект iTestResult для получения информации о тесте. Логика: если тест упал и попыток < MAX → return true (повтор)
4. Можно указать это в TestNG xml файл чтобы iRetryAnalyzer применялся при запуске. @Test(retryAnalyzer = Retry.class)

public class Retry implements IRetryAnalyzer {
    private int attempt = 1;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                // При перезапуске теста в консоль выведется:
                    System.out.println("Повторная попытка запуска");
                // Это помогает отслеживать, сколько раз тест перезапускался
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}
4. Используем параметр retryAnalyzer для подключения класса к методу
@Test(retryAnalyzer = Retry.class)
public void dependsOn() {
    throw new NullPointerException();
}

# Как реализовать подключение Retry через TestNG.xml файл - через глобальный Retry через TestNG.xml (IAnnotationTransformer)
https://swtestacademy.com/retry-failed-tests-testng-iretryanalyzer/
1. Создать класс AnnotationTransformer в том же пакете. Этот класс будет динамически добавлять Retry ко всем тестам во время выполнения.
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,Constructor testConstructor, Method testMethod) {
        // Устанавливаем Retry анализатор для каждого тестового метода
        annotation.setRetryAnalyzer(Retry.class);
    }
}

2. Добавить слушатель в основной testng.xml файл конфигурации который будет использоваться. Добавить в  секцию <listeners>.
    <!-- !!! СЮДА ДОБАВИТЬ СЛУШАТЕЛЯ !!! -->
    <listeners>
        <listener class-name="homework5_TestNG.tests.AnnotationTransformer"/>
    </listeners>

3. Аннотации @Test(retryAnalyzer = Retry.class) над классами/методами можно убрать.
     */
}
