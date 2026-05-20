package lesson10.pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class LoginPage {

    //    Page Factory
    WebDriver driver;
    // инициализируются веб-элементы единожды, объявляются локаторы через в аннотации @FindBy. Локатор может быть только статичным
    @FindBy(id = "user_name")
    private WebElement loginField;
    @FindBy(id = "username_password")
    private WebElement passwordField;
    @FindBy(name = "Login")
    private WebElement loginButton;

    // в конструктор нужно добавить PageFactory.initElements(driver, this);
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        log.info("LoginPage open");

        driver.get("https://demo.suiteondemand.com/index.php?module=Users&action=Login");

    }

    public void login(String user, String password) {
        log.info("Login credentials for user '{}' and password '{}'", user, password);
        loginField.sendKeys(user);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
