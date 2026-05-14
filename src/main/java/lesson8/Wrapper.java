package lesson8;

public class Wrapper {

    /*
# Обёртки (Wrappers) — это:
- Уровень абстракции над UI-элементами
- Способ избежать дублирования локаторов
- Инструмент для создания гибких Page Object'ов

Пакет wrapper находится в директории java, там же где и pages проекта.
Сначала в девтулс нудно понять структуру локатора, в написании локатора сначала отталкиваемся от имени заполняемого поля,
 а затем перейти к инпуту, то есть в таком локаторе только 'Office Phone' будет отличаться в названии инпутов.
By.xpath("//div[contains(text(), 'Office Phone')]/parent::div//input")
затем видоизменяем его на
String.format("//div[contains(text(), '%s')]/parent::div//input", label)), где
'%s' - подстановочная переменная

Создание класса обертки типа Input
public class Input {

    WebDriver driver;
    //  label это название поля, которое будет меняться
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    //    метод позволяющий заполнять любые инпуты различными данными - передаваемого в параметрах text, а имя инпута подставляется в '%s'
    public void write(String text) {
        // driver.findElement(By.xpath("//div[contains(text(), 'Office Phone')]/parent::div//input")).sendKeys(text);
        driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]/parent::div//input", label))).sendKeys(text);
    }
}

Применение в тестах
- Способ 1 (классический) — создаём переменную:
Input input = new Input(driver, "Name");
input.write("test");
- Способ 2 (однострочный) — Можно использовать в одну строку, если объект нужен только для одного действия. Если объект
используется несколько раз — лучше создать переменную по Способу 1.
new Input(driver, "Name").write("test");
Конструктор класса Input возвращает новый объект класса Input. А у этого объекта сразу вызывается метод write(). По сути,
 это две операции в одной строке:
new Input(driver, "Name") → создаётся объект
.write("test") → вызывается метод на этом объекте

# 1. Пример обёртки для кнопки (Button):
public class Button {
    WebDriver driver;
    String buttonText;

    public Button(WebDriver driver, String buttonText) {
        this.driver = driver;
        this.buttonText = buttonText;
    }

    public void click() {
        driver.findElement(By.xpath(String.format("//button[text()='%s']", buttonText))).click();
    }
}
2. Пример обёртки для выпадающего списка (Dropdown):
public class Dropdown {
    WebDriver driver;
    String label;

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String optionText) {
        WebElement dropdown = driver.findElement(By.xpath(String.format("//label[text()='%s']/following-sibling::select", label)));
        new Select(dropdown).selectByVisibleText(optionText);
    }
}


     */
}
