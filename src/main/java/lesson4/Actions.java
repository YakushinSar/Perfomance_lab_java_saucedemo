package lesson4;

public class Actions {
    /*
Actions — это класс в Selenium, для имитации реальных действий пользователя (мышь + клавиатура), когда обычного click()
 недостаточно. Если нужно выполнить несколько действий подряд, их можно собрать в цепочку:
action.moveToElement(element)
      .clickAndHold()
      .moveByOffset(100, 0)
      .release()
      .perform();
В конце цепочки обязательно нужен .perform() — он выполняет все накопленные действия, если .perform() не вызвать — действия не произойдут

# Как работать: Создание объекта Actions
Actions action = new Actions(driver);

 # Действия
click() - клик
doubleClick() - Двойной клик
contextClick() - Клик правой кнопкой (контекстное меню)
clickAndHold() - Удержание кнопки мыши
moveToElement() - Наведение мыши на элемент
dragAndDrop() - Перетаскивание (drag & drop)
keyDown()/keyUp() - Зажатие клавиши, указывается в скобках (Ctrl, Shift), сначала нажимаем, а потом отжимаем

dragAndDrop(sourceElement, targetElement) - Перетаскивание элементов
scrollToElement(element) - Скроллинг к элементу
scrollByAmount(deltaX, deltaY) - Скроллинг по координатам
#Работа с клавиатурой
sendKeys(element, Keys.ENTER) -
keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform(); - Удержание Ctrl + клик
sendKeys(Keys.ESCAPE) - Нажатие клавиши без привязки к элементу
perform() - Обязательно — выполнить все накопленные действия

https://www.selenium.dev/documentation/webdriver/actions_api/keyboard/
     */
}
