package lesson4;

public class FileUpload {
    /*
By.xpath("//input[@type='file']") — это стандартный, надёжный способ найти поле загрузки файла type='file'. Если их несколько — нужно
 уточнить (например, по id или name)

# Как безопасно указать путь к файлу: Пути только относительные. Абсолютный путь (C:\Users\...) работает только на локальном компьютере
Вариант 1: ручной относительный путь
WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
fileInput.sendKeys("src/test/resources/files/myfile.png");

Вариант 2: через File (кросс-платформенно), лучше так делать!!!
WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
File file = new File("src/test/resources/files/myfile.png");
fileInput.sendKeys(file.getAbsolutePath());

● Разделитель между папками/файлами в пути ТОЛЬКО "/" либо File.separator
String path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "files" + File.separator + "myfile.png";
fileInput.sendKeys(path);

WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']")); - Найти поле загрузки файла - найти элемент <input type="file">
fileInput.sendKeys("src/test/resources/files/myfile.png"); - Указать путь к файлу (ТОЛЬКО относительный) через sendKeys()
driver.findElement(By.cssSelector("#upload-button")).click(); - Нажать кнопку «Загрузить» (если она есть). Иногда кнопка загрузки не нужна — файл начинает загружаться автоматически сразу после выбора. В этом случае шаг 3 пропускается.
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader"))); - Дождаться, пока индикатор загрузки исчезнет, загрузка завершена
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".success-message"))); - Если индикатор загрузки не исчезает, а появляется сообщение об успехе, можно ждать его появления
     */
}
