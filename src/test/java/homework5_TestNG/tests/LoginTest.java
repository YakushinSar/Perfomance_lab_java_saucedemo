package homework5_TestNG.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
public class LoginTest extends BaseTest {

    @Test(description = "Проверка входа в систему с валидными данными",
            testName = "Успешный вход",
            priority = 1)
    public void checkLoginWithPositiveCred() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Негативные данные для логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Негативные данные для логина",
            description = "Негативные проверки логина",
            testName = "Ошибка при логине, параметризированный тест",
            priority = 2)
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.openPage();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
