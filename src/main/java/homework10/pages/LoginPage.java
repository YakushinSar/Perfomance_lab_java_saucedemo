package homework10.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.xpath("//input[@id='user-name']");
    private final By PASSWORD_FIELD = By.xpath("//input[@id='password']");
    private final By LOGIN_BUTTON = By.xpath("//input[@id='login-button']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");
    private final By PAGE_LOADED_INDICATOR = By.xpath("//input[@id='login-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Загрузка страницы Login")
    public LoginPage isPageOpened() {
        log.info("Waiting for Login page to load");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    @Step("Открытие страницы Login")
    public LoginPage openPage() {
        log.info("Opening login page: {}", BASE_URL);
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вход в магазин с данными : логин '{user}', пароль '{password}'")
    public ProductsPage login(String username, String password) {
        log.info("Logging in with username: '{}'", username);
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}

