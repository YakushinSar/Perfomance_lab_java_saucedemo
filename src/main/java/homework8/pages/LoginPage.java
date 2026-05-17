package homework8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    @Step("Открытие страницы Login")
    public void openPage() {
        driver.get(BASE_URL);
    }

    @Step("Вход в магазин с данными : логин '{user}', пароль '{password}'")
    public void login(String username, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}

