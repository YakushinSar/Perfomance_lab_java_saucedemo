package lesson5TestNG.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends BaseTest {

    @Test(enabled = true,
            description = "проверка входа в систему с позитивными кредами",
            testName = "проверка с позитивными данными",
            invocationCount = 9,
            threadPoolSize = 3)// можно переносить на другую строку для читабельности
    public void checkLoginWithPositiveCred() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "secret_sauce");

        assertEquals(getProductsPage().getTitle(), "Products");
    }

    @Test(priority = 3,
            groups = {"smoke", "slow"},
            retryAnalyzer = Retry.class)
    public void checkLoginWithEmptyPassword() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "");
        assertEquals(getLoginPage().getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(priority = 2,
            groups = {"regression"})
    public void checkLoginWithEmptyUser() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("", "secret_sauce");
        assertEquals(getLoginPage().getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(priority = 1,
            description = "проверка входа в систему с позитивными кредами",
            testName = "проверка с негативными данными",
            groups = {"regression"})

    public void checkLoginWithNegativeCred() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("test", "test");
        assertEquals(getLoginPage().getErrorMessage(), "Epic sadface: Username and password do not match any user in" +
                " this service");
    }

    @Test
    public void login() {
        throw new RuntimeException("test failed");
    }

    @Test(dependsOnMethods = "login", alwaysRun = true)
//тест не запустится, так как он зависит от теста login, запустится ТОЛЬКО с alwaysRun = true
    public void checkProfile() {
        System.out.println("test passed");
    }

    // набор параметров для параметизированных тестов
    @DataProvider(name = "Тестовые данные для негативного логина", indices = {0, 2})
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина")
    // Параметризованные тест, так как у теста есть параметры. При запуске теста они будут браться из тестового набора loginData
    public void negativeLogin(String user, String password, String errorMessage) {
        getLoginPage().open();
        getLoginPage().login(user, password);
        assertEquals(getLoginPage().getErrorMessage(), errorMessage);
    }
}
