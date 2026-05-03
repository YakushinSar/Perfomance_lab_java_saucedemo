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

# Как реализовать подключение Retry через TestNG.xml файл
https://swtestacademy.com/retry-failed-tests-testng-iretryanalyzer/

#  Можно указать это в TestNG xml файл чтобы iRetryAnalyzer применялся при запуске
Сначала добавляем класс в BaseTest
public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(Retry.class);
    }
}
В TestNG xml файл прокинуть  iRetryAnalyzer в теги Listener
<listeners>
        <listener class-name="retrysingletest.Listener"/>
        <listener class-name="retrysingletest.AnnotationTransformer"/>
    </listeners>
     */
}
