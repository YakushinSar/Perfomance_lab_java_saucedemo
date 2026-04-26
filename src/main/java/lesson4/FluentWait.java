package lesson4;

public class FluentWait {
    /*
Fluent Wait - Гибкие ожидания — это тип явных ожиданий, который позволяет:
- Задать общий максимальный таймаут ожидания (сколько максимум ждать элемент).
- Задать периодичность проверок (через какие интервалы времени Selenium будет проверять условие).
- Настроить игнорирование исключений, которые могут возникнуть во время ожидания (например, NoSuchElementException).

Wait<WebDriver> fluent = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(10))
    .pollingEvery(Duration.ofSeconds(5))
    .ignoring(NoSuchElementException class);
WebElement foo = fluent.until(driver ->
driver.findElement(By.id("foo")));

     */
}
