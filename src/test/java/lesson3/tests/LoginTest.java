package lesson3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    @Test
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");

//        driver.get("https://www.saucedemo.com/");
//        WebElement nameInput = driver.findElement(By.xpath("//*[@data-test='username']"));
//        nameInput.sendKeys("standard_user");
//        WebElement passwordInput = driver.findElement(By.xpath("//*[@data-test='password']"));
//        passwordInput.sendKeys("secret_sauce");
//        WebElement loginBtn = driver.findElement(By.xpath("//*[@data-test='login-button']"));
//        loginBtn.click();
//
//        String title = driver.findElement(By.cssSelector("[data-test=title]")).getText();
//        assertEquals(title, "Products");
    }

    @Test
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");

//        driver.get("https://www.saucedemo.com/");
//        WebElement nameInput = driver.findElement(By.xpath("//*[@data-test='username']"));
//        nameInput.sendKeys("standard_user");
//        WebElement passwordInput = driver.findElement(By.xpath("//*[@data-test='password']"));
//        passwordInput.sendKeys("");
//        WebElement loginBtn = driver.findElement(By.xpath("//*[@data-test='login-button']"));
//        loginBtn.click();
//        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();
//        assertEquals(errorMessage, "Epic sadface: Password is required");
    }

    @Test
    public void checkLoginWithEmptyUser() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in" +
                " this service");
    }
}
