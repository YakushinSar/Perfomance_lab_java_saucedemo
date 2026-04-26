package lesson4;

public class Frames {
    /*
IFrame - это когда страница находится внутри другой страницы. Например, это может быть элемент какой-либо реализованной логики,
  которую чтобы не создавать заново взяли для использования на сайте. В дом дереве фреймы оборачиваются в тег "iframe". Чтобы
  работать с фреймом - надо сначала переключится на него, провести необходимые манипуляции, а затем выйти из фрейма. Если
  не выйти из iFrame, дальнейшие поиски элементов будут вестись внутри него, а не на основной странице

Работа с frame:
•Получение ссылки на iframe
WebElementiframe= driver.findElement(By.cssSelector("#modal>iframe"));
•Переключение в iframe
driver.switchTo().frame(iframe); // Переключение в `iframe` по элементу
driver.switchTo().frame("buttonframe");// Переключение в `iframe` по `id`
driver.switchTo().frame("myframe");// Переключение в `iframe` по `name`
driver.switchTo().frame(1); // Переключение в `iframe` по индексу
•Возврат в основной контекст
driver.switchTo().defaultContent();

https://www.selenium.dev/documentation/webdriver/interactions/frames/
     */
}
