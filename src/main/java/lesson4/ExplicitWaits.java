package lesson4;

public class ExplicitWaits {
    /*
Ожидания НЕ являются проверками, они используются для работы с элементами. Ассерт является проверкой.
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

     */
}
