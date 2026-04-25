package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    //успешный вход с валидными данными
    public void checkLoginWithPositiveCred() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    //отображается ошибка при пустом поле Password
    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.openPage();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    //отображается ошибка при пустом поле Username
    @Test
    public void checkLoginWithEmptyUser() {
        loginPage.openPage();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    //отображается ошибка при неверных данных
    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.openPage();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
    }
}
