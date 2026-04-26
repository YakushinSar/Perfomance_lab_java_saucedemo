package lesson4;

public class Js {
    /*
# Вкладка ‘Console’ браузера позволяет выполнять JS-код прямо на странице, где вы находитесь. Примеры полезных запросов ниже:
document.getElementById('add-to-cart-sauce-labs-fleece-jacket').click() //Получение всех атрибутов элемента
document.readyState //Получение состояния загрузки страницы

# JavascriptExecutor в Selenium — инструмент, который позволяет напрямую выполнять JavaScript-код в контексте браузера.
Это особенно полезно в тех случаях, когда стандартные методы WebDriver не справляются: нестандартные элементы, скрытые кнопки,
проблемы с прокруткой, элемент перекрыт открытым окном и т.д. JavascriptExecutor не ждёт загрузки страницы или элементов
Возвращаемое значение нужно приводить к типу (например, (String))
1. Клик по элементу - полезен для тестировщика
JavascriptExecutor js = (JavascriptExecutor) driver;
WebElement element = driver.findElement(By.id("username"));
js.executeScript("arguments[0].click();", element);

2. Скроллинг до элемента - полезен для тестировщика
js.executeScript("arguments[0].scrollIntoView(true);", element);
js.executeScript("window.scrollBy(0,500);" );

3. Получение значения из элемента (например, value input’а)
String value = (String) js.executeScript("return arguments[0].value;", element);

4. Установка значения в поле
js.executeScript("arguments[0].value='новое значение';", element);

5. Прокрутка до конца страницы (полезно для ленивой загрузки контента) - полезен для тестировщика
js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

6. Получение HTML или текста элемента
String innerText = (String) js.executeScript("return arguments[0].innerText;", element);
String innerHTML = (String) js.executeScript("return arguments[0].innerHTML;", element);

# Ожидание загрузки JS (Ajax) — технология, которая позволяет обновлять часть страницы без её полной перезагрузки.
При использовании AJAX:
● Пользователь заходит на веб-страницу и нажимает на какой-нибудь её элемент.
● Скрипт (на языке JavaScript) определяет, какая информация необходима для обновления страницы.
● Браузер отправляет соответствующий запрос насервер.
● Сервер возвращает только ту часть документа, на которую пришёл запрос.
● Скрипт вносит изменения с учётом полученной информации (без ПОЛНОЙ перезагрузки страницы).

# document.readyState — надёжный способ дождаться полной загрузки страницы. Что делает: Ждёт, пока состояние документа
 (document.readyState) станет "complete" (страница полностью загружена).

public void waitForPageLoaded() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver driver) {
            return ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete");
        }
    });
}

     */
}
