package lesson4;

public class Alert {
    /*
# Alert - это всплывающее окно JavaScript, которое требует немедленного действия пользователя (подтверждение, отмена, ввод текста).
-Alert нельзя найти через findElement, это не HTML-элемент, а окно браузера.
-Alert нужно обязательно подтвердить или отклонить - наче тест зависнет
-sendKeys() работает только для prompt - для alert() и confirm() не имеет смысла

Alert alert = driver.switchTo().alert(); // Переключиться на Alert
alert.accept(); // Нажать OK (подтвердить)
alert.dismiss(); // Нажать Cancel (отклонить)
String text = alert.getText(); // Получить текст сообщения
alert.sendKeys("текст"); // Ввести текст (только для prompt)
alert.accept(); // После ввода нужно подтвердить

# Пример проверки текста в Alert:
Alert alert = driver.switchTo().alert();
String actualText = alert.getText();
assertEquals("Ожидаемый текст", actualText);
alert.accept();

     */
}
