package lesson4;

public class ExplicitWaits {
    /*
# Не смешивай неявные и явные ожидания. Золотое правило автоматизации на Selenium: Забудь про implicitlyWait. Используй
 только WebDriverWait + ExpectedConditions — они покрывают все сценарии. Неявное ожидание добавляет свою задержку ко времени
 выполнения явного ожидания, потому что каждый вызов findElement внутри явного ожидания сначала отрабатывает с неявным таймаутом.
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // неявное
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // явное
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("someId")));
Что происходит:
visibilityOfElementLocated вызывает findElement. findElement ждёт до 10 секунд (неявное ожидание), если элемент не появляется
Только потом явное ожидание начинает считать свои 5 секунд. Итого: до 15 секунд ожидания
# Когда можно использовать неявные ожидания (редкий случай):
-Статические страницы — весь HTML загружается сразу
-Нет AJAX, нет подгрузки контента
-Элементы не появляются динамически после загрузки страницы

Ожидания НЕ являются проверками, они используются для работы с элементами, ожидания — для «дождаться». Ассерты — для «проверить».
Это не взаимозаменяемые вещи.
1. Явное ожидание — ждём, пока элемент станет видимым
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
2. Ассерт — проверяем текст
assertEquals("Успешно", driver.findElement(By.id("message")).getText());

Явные ожидания (WebDriverWait) - ожидания с указанием определенных условий, например видимость элемента либо его исчезновение.
 В отличии от неявного ожидания, которое объявляется  один раз - явные ожидания применяются конкретно к каждому элементу.
WebDriverWait wait = new WebDriverWait(driver, Duration. ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id")));
# wait.until - переводится как - ждем пока ожидаемое условие(ExpectedConditions.) не будет выполнено -  и через точку пишем нужное условие, например .visibilityOfElementLocated

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    public final String BASE_URL = "https://www.saucedemo.com/";
    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}

# Типы ExpectedConditions.
- visibilityOf(WebElement element) - Ждёт, пока элемент станет видимым
visibilityOfElementLocated(By locator) - Ждёт, пока элемент по локатору станет видимым
visibilityOfAllElements(List<WebElement> elements) - Ждёт, пока все элементы станут видимыми
invisibilityOf(WebElement element) - Ждёт, пока элемент станет невидимым
invisibilityOfElementLocated(By locator) - Ждёт, пока элемент по локатору исчезнет

- attributeToBe(By locator, String attribute, String value) - Ждёт, пока у элемента появится атрибут с указанным значением
attributeContains(By locator, String attribute, String value) - Ждёт, пока атрибут элемента будет содержать указанную подстроку
attributeToBe(WebElement element, String attribute, String value) - Ждёт, пока у элемента появится атрибут с указанным значением
attributeContains(WebElement element, String attribute, String value) - Ждёт, пока атрибут элемента будет содержать указанную подстроку

- numberOfElementsToBe(By locator, int number) - Ждёт, пока количество элементов по локатору станет равно указанному числу
numberOfWindowsToBe(int expectedNumberOfWindows) - Ждёт, пока количество открытых окон в браузере станет равно указанному числу

- frameToBeAvailableAndSwitchToIt(By locator) - Ждёт, пока фрейм станет доступен, и переключается на него по локатору
frameToBeAvailableAndSwitchToIt(int frameIndex) - Ждёт, пока фрейм станет доступен, и переключается на него по индексу
frameToBeAvailableAndSwitchToIt(String frameNameOrId) - Ждёт, пока фрейм станет доступен, и переключается на него по имени или ID
frameToBeAvailableAndSwitchToIt(WebElement frameElement) - Ждёт, пока фрейм станет доступен, и переключается на него по WebElement

И многое-многое другое
- textToBe(By locator, String value) - Ждёт, пока у элемента появится указанный текст
textToBePresentInElement(By locator, String text) - Ждёт, пока элемент будет содержать указанный текст
alertIsPresent() - Ждёт, пока появится всплывающее окно alert
elementToBeSelected(By locator) - Ждёт, пока элемент (checkbox/radio) станет выбранным
elementToBeClickable(By locator) - Ждёт, пока элемент станет видимым и доступным для клика
titleIs(String title) - Ждёт, пока заголовок страницы станет точно равен указанному
titleContains(String title) - Ждёт, пока заголовок страницы будет содержать указанную подстроку

# Как проверить, что элемента нет на странице? Можно использовать комбинацию ниже
//Уменьшаем таймаут ожидания
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//Проверяем, что коллекция пуста
int numberOfElements = driver.findElements(By.tagName("локатор")).size();
assertEquals(numberOfElements, 0, "Элемент присутствует на странице");
//Возвращаем таймаут
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


https://www.selenium.dev/documentation/webdriver/waits/

# Стратегии ожиданий в Selenium
•Normal–стандартная стратегия, при которой Selenium ожидает полной загрузки страницы, включая все элементы, стили и скрипты,
 прежде чем продолжить выполнение теста.
•Eager–тест продолжает выполнение сразу после загрузки DOM, даже если внешние ресурсы (CSS, JavaScript, изображения) еще
 не подгрузились. Позволяет ускорить тестирование, если взаимодействие с элементами возможно без полной загрузки страницы.
•None–отключает ожидание загрузки страницы после выполнения навигационной команды. Seleniumсразу возвращает управление,
 даже если загрузка еще продолжается. Используется в специфических сценариях, например, при тестировании одностраничных приложений (SPA) с динамической подгрузкой контента.
https://www.selenium.dev/documentation/webdriver/drivers/options/#pageloadstrategy

# Настройки браузера (Browser Options)в Selenium
•setPlatformName–указывает операционную систему, на которой будет запущен браузер при удаленном выполнении тестов.
•setBrowserVersion–позволяет задать конкретную версию браузера для запуска тестов.
•setPageLoadStrategy–определяет стратегию загрузки страницы (Normal, Eager, None).
•setAcceptInsecureCerts–активирует флаг, разрешающий работу с небезопасными SSL-сертификатами.
•setPageLoadTimeout–устанавливает максимальное время ожидания полной загрузки страницы.
•setScriptTimeout–определяет предельное время выполнения JavaScript-кода в браузере.
•setImplicitWaitTimeout–задает время ожидания элементов при использовании неявных ожиданий (ImplicitWaits).
https://www.selenium.dev/documentation/webdriver/drivers/options/
     */
}
