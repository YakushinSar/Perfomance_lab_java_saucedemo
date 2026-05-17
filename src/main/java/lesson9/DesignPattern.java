package lesson9;

public class DesignPattern {
    /*
# Design Pattern - это повторяемые, проверенные временем архитектурные решения, которые помогают строить поддерживаемые, масштабируемые и
читаемые тестовые фреймворки. Зачем нужны дизайн-паттерны в автоматизации:
- Упрощают сопровождение автотестов;
- Повышают переиспользуемость кода;
- Помогают разделить ответственность в проекте;
- Делают структуру проекта понятной для других участников команды;
- Ускоряют разработку благодаря стандартным подходам.

Design-patterns в автоматизации:
# Page Object
Проблемы:
1. Код находится в одном классе, что затрудняет совместную работу
2. Список доступных действий в тесте слишком широкий
3. Код тестов нечитабелен
Решение:
1. Каждая страница/модалка/порой даже некая область на странице - выносится в отдельный класс. В данном классе только локаторы и методы для
взаимодействия

# Page Factory
Проблемы:
В классическом Page Object приходится постоянно писать driver.findElement(By...). Это приводит к дублированию кода и снижает читаемость.
Решение:
Инициализация всех элементов страницы происходит однократно с помощью аннотаций @FindBy.
public class LoginPage {
    WebDriver driver;
    // Аннотация @FindBy — поиск элемента по локатору
    @FindBy(id = "user_name")
    private WebElement loginField;
    @FindBy(id = "username_password")
    private WebElement passwordField;
    @FindBy(name = "Login")
    private WebElement loginButton;

    // Конструктор — инициализация элементов через PageFactory
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // ← ключевой момент
    }

    public void open() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Users&action=Login");
    }
    public void login(String user, String password) {
        loginField.sendKeys(user);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
Как это работает:
1. PageFactory.initElements(driver, this) создаёт "прокси-объекты" для всех полей с @FindBy
2. При первом обращении к полю (loginField.sendKeys) — прокси вызывает driver.findElement()
3. Элемент находится один раз и кэшируется для следующих вызовов
Другие аннотации Page Factory:
@FindBy - Поиск элемента по одному локатору,  @FindBy(id = "username")
@FindBys - логическое И — все условия должны совпасть, @FindBys({@FindBy(className = "form"), @FindBy(name = "login")})
@FindAll - логическое ИЛИ — хотя бы одно условие, @FindAll({@FindBy(id = "login"), @FindBy(name = "login")})

# Loadable Page/Component
Проблема:
После загрузки страницы не все элементы могут быть сразу доступны.
Тест падает из-за попытки взаимодействия с ещё не загруженным элементом.
Решение:
-Создаётся абстрактный базовый класс BasePage с методом isPageOpened().BasePage как тип возвращаемого значения означает,
 что метод isPageOpened() возвращает объект типа BasePage (или любого его наследника).
-В каждом Page Object переопределяется этот метод с явным ожиданием уникального элемента страницы.isPageOpened() вызывается
 ПЕРЕД каждым взаимодействием со страницей
-Метод openPage() открывает страницу и возвращает её объект. Возврат this — позволяет делать цепочки вызовов (но это уже Chain of Invocations)
Loadable Page не заменяет явные ожидания, а организует их использование.
-Явные ожидания — это инструмент
-Loadable Page — это правило, как использовать инструмент
Без паттерна можно работать (и многие так делают), но с паттерном код становится предсказуемее и надёжнее. Особенно когда
 тестов много и их пишет команда.

// 1. В BasePage — абстрактный метод
public abstract class BasePage {
    public abstract BasePage isPageOpened();
}

// 2. В каждом наследнике — конкретная проверка загрузки
public class LoginPage extends BasePage {
    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        return this;
    }
}
// Использование в тесте :
LoginPage loginPage = new LoginPage(driver);
loginPage.openPage();      // Открыли страницу
loginPage.isPageOpened();  // Дождались загрузки

ProductsPage productsPage = loginPage.login("user", "pass");
productsPage.isPageOpened();  // ← ждём загрузки следующей страницы


# Fluent/Chain of invocations
Проблемы:
При вызове методов из Page Object не всегда ясно какой за каким следует использовать, а также приходится писать дублирующийся вызов
переменной
Решение:
После вызова метода - возвращать тот же либо другой объект (если происходит переход на новую страницу) как возвращаемый
 параметр метода. Цепочка вызовов метода может прерываться и это нормальная практика.
// Без Fluent:
page.open();
page.login();
page.wait();

// С Fluent:
page.open()
    .login()
    .wait();

Как реализовать:
Каждый метод должен возвращать this (текущий объект). Метод openPage() открывает страницу и возвращает тот же самый объект
 LoginPage, чтобы можно было продолжить вызывать его методы.
public class LoginPage {
    WebDriver driver;
    public  open() {
        driver.get("https://demo.suiteondemand.com/");
        return this;  // ← возвращаем текущий объект, LoginPage
    }
    public LoginPage login(String user, String password) {
        driver.findElement(By.id("user_name")).sendKeys(user);
        driver.findElement(By.id("username_password")).sendKeys(password);
        driver.findElement(By.name("Login")).click();
        return this;  // ← возвращаем текущий объект
    }
    public LoginPage waitForPageLoad() {
        // ожидание загрузки страницы
        return this;
    }
}

Если метод переходит на другую страницу:
public NewAccountPage clickCreateAccount() {
    createAccountButton.click();
    return new NewAccountPage(driver);  // ← новая страница!, возвращаем новый объект
}
// Визуально с типами:
LoginPage    ──login()──>    ProductsPage
   ↑                             ↑
return this              return new ProductsPage(driver)

Использование:
newAccountPage = new LoginPage(driver)
    .login("will", "will")
    .clickCreateAccount()      // ← переход на NewAccountPage
    .addNewAccount(account);   // ← метод уже из NewAccountPage
где
// Тип переменной = тип результата последнего вызова в цепочке
productsPage = new LoginPage(driver)  // LoginPage
    .openPage()                       // LoginPage
    .isPageOpened()                   // LoginPage
    .login("user", "pass")            // ProductsPage ← определяет тип
    .isPageOpened();                  // ProductsPage
//  ↑
// productsPage имеет тип ProductsPage

# Value Object/DTO
Проблемы:
1. Методы принимают СЛИШКОМ МНОГО параметров одинакового типа, очередность неясна
2. При добавлении нового параметра — нужно обновлять ВСЕ места вызова
Решение:
Создать отдельный класс DTO (Data Transfer Object), который группирует связанные поля. Методы работают с объектом, а не с кучей параметров.
Как реализовать:
1. Создать класс в пакете `dto`. DTO должен быть просто "контейнером" для данных, которые не должны меняться после создания.
2. Добавить поля, конструктор и геттеры
3. DTO не должен содержать бизнес-логику — только данные
4. В тесте создать объект DTO и передать в метод

Пример:
// ❌ Без DTO — при добавлении поля нужно менять ВСЕ места вызова
page.addNewAccount("test", "+123456", "avito.ru", "test@mail.com");  // новый параметр
// ✅ С DTO — меняем только конструктор
Account account = new Account("test", "+123456", "avito.ru", "test@mail.com");
page.addNewAccount(account);

Короткое пояснение:
// Создаём класс DTO. В DTO — только данные и геттеры, никакой логики! Рекомендуется делать поля final (неизменяемый объект)
public class Account {
    private String name;
    private String phone;
    private String website;

    public Account(String name, String phone, String website) {
        this.name = name;
        this.phone = phone;
        this.website = website;
    }

    // Геттеры...
}

// Метод принимает объект
public void addNewAccount(Account account) {
    new Input(driver, "Name").write(account.getName());
    new Input(driver, "Office Phone").write(account.getPhone());
    new Input(driver, "Website").write(account.getWebsite());
}

# Page Element/Wrappers
Обёртки (Wrappers) — это:
- Уровень абстракции над UI-элементами
- Способ избежать дублирования локаторов
- Инструмент для создания гибких Page Object'ов

# Builder/Factory
Builder — для создания сложных объектов с множеством параметров
User user = User.builder()
    .name("Иван")
    .email("test@mail.com")
    .build();

Factory — для создания объектов без указания конкретного класса
WebDriver driver = DriverFactory.getDriver("chrome");

     */
}
