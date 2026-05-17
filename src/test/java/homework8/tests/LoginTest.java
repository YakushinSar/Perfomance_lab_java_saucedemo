package homework8.tests;

import homework8.utils.Retry;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
public class LoginTest extends BaseTest {

    @Test(description = "Проверка входа в систему с валидными данными",
            testName = "Успешный вход",
            priority = 1)
    @Description("Проверка входа в систему с валидными данными")
    @Feature("Login in SauseDemo")
    @Story("Успешный вход")
    @TmsLink("ID-1")
    @Issue("ID-1")
    @Owner("Якушин")
    public void checkLoginWithPositiveCred() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageOpened();

        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Негативные данные для логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"lesson9/test", "lesson9/test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Негативные данные для логина",
            description = "Негативные проверки логина",
            testName = "Ошибка при логине, параметризированный тест",
            priority = 2)
    @Description("Негативные данные для логина. Ошибка при логине, параметризированный тест")
    @Feature("Login in SauseDemo")
    @Story("Негативные проверки логина")
    @TmsLink("ID-2")
    @Issue("ID-2")
    @Owner("Якушин")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
