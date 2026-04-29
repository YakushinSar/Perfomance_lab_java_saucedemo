package lesson5;

public class iRetryAnalyzer {
    /*
1. Имплементируем интерфейс iRetryAnalyzer
2. Имплементируем метод retry как нам заблагорассудится
3. Используем объект iTestResult для получения информации о тесте
public class Retry implements IRetryAnalyzer {

    private int attempt = 1;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                System.out.println("Retrying once again");
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


     */
}
