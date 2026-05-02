package lesson5JUnit5.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_FIELD = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BUTTON = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    //    Явные ожидания (WebDriverWait)
    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }

    public void login(String user, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
