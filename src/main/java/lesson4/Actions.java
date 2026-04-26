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

 # Действия
doubleClick() - Двойной клик
contextClick() - Клик правой кнопкой (контекстное меню)
moveToElement() - Наведение мыши на элемент
dragAndDrop() - Перетаскивание (drag & drop)
keyDown()/keyUp() - Зажатие клавиши, указывается в скобках (Ctrl, Shift), сначала нажимаем, а потом отжимаем
clickAndHold() - Удержание кнопки мыши
perform() - Обязательно — выполнить все накопленные действия

# Как работать:
Actions action = new Actions(driver);

action.moveToElement(element).perform(); // Навести мышь на элемент
action.doubleClick(element).perform(); // Двойной клик
action.dragAndDrop(source, target).perform(); // Перетаскивание
action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).perform(); // Удержание Ctrl + клик

     */
}
