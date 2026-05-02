package lesson5TestNG.testNg;

public class ITestListenerTestNG {
    /*
# ITestListener - это класс, который нужен для того, чтобы следить за жизненным циклом теста (тест начался, за какое время
 прошел, упал и тд).
1. Имплементируем интерфейс ITestListener
2. Перезаписываем ВСЕ методы, каждый отдельный отвечает за статус теста
3. Над BaseTest указываем аннотацию @Listeners и указываем там наш класс - @Listeners(TestListener.class)
4. При запуске тестов в консоли будет теперь залогированная информация о запущенных тестах.
5. Можно указать это в TestNG xml файл чтобы ITestListener применялся при запуске <listeners>

ITestResult - хранит в себе всю информацию о прохождении теста
ITestContext - хранит в себе всю информацию о всех тестах которые запускались в рамках текущего прогона
onTestStart срабатывает	перед тестом
onTestSuccess срабатывает если тест пройден
onTestFailure срабатывает если тест упал
onTestSkipped срабатывает если тест пропущен
onStart/onFinish срабатывает до/после всех тестов


public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.printf("======================================== STARTING TEST %s ========================================%n", iTestResult.getName());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.printf("======================================== FINISHED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.printf("======================================== SKIPPING TEST %s ========================================%n", iTestResult.getName());
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }
    @Override
    public void onStart(ITestContext iTestContext) {
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
    }
    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}

# Можно указать это в TestNG xml файл чтобы ITestListener применялся при запуске
    <listeners>
        <listener class-name="lesson5.tests.TestListener"/>
    </listeners>


     */
}
