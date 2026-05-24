package lesson10.step;

import lesson10.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginStep {

    //    в step создается метод для попадания на определенную страницу, чтобы в тесте не писать несколько методов
    WebDriver driver;
    LoginPage loginPage;

    public LoginStep(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
    }

    public void auth(String user, String password) {
        loginPage.open();
        loginPage.login(user, password);
    }
}
