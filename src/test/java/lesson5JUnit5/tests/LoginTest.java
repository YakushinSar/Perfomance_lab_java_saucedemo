package lesson5JUnit5.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends BaseTest {

    @Test
    @DisplayName("логин с пустым паролем")
    @Disabled
    @Tag("soke")
    @Order(1) // нужна аннотация для класса @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public void checkLoginWithPositiveCred() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "secret_sauce");

        assertEquals(getProductsPage().getTitle(), "Products");
    }


    @RepeatedTest(3) // аннотация @Test при использовании вместе не нужна
    public void checkLoginWithEmptyPassword() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "");
        assertEquals(getLoginPage().getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test
    public void checkLoginWithEmptyUser() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("", "secret_sauce");
        assertEquals(getLoginPage().getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
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


    public void checkProfile() {
        System.out.println("test passed");
    }

    //    параметризированный тест
    @ParameterizedTest
    @CsvSource(
            "test, test, Epic sadface: Username and password do not match any user in this service"
    )
    public void checkLoginWithNegativeCred1(String user, String password, String message) {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login(user, password);  // ← использую параметры метода
        assertEquals(getLoginPage().getErrorMessage(), message);
    }

    //софт assertions
    @Test
    @DisplayName("Проверка логина с софт assertions")
    public void testLoginWithSoftAssertions() {
        getLoginPage().open();
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "secret_sauce");

        //софт assertions: обе выполнятся, даже если первая упадёт
        assertAll("Проверка успешного входа",
                () -> assertEquals("Products", getProductsPage().getTitle(), "Заголовок страницы не совпадает"),
                () -> assertEquals("https://www.saucedemo.com/inventory.html", getLoginPage().getCurrentUrl(), "URL не совпадает")
        );
    }

}
