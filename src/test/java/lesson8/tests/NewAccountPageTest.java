package lesson8.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class NewAccountPageTest extends BaseTest {
    @Test
    public void checkAddNewAccount() throws InterruptedException {
        driver.get("https://demo.suiteondemand.com/index.php?module=Users&action=Login");
        driver.findElement(By.id("user_name")).sendKeys("will");
        driver.findElement(By.id("username_password")).sendKeys("will");
        driver.findElement(By.name("Login")).click();
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");

        sleep(2000);
        newAccountPage.addNewAccount("lesson9/test", "+123456", "+1234567", "avito.ru", "lesson9/test", "Investor", "Banking");
        newAccountPage.clickSave();  // вызов метода сохранения
        sleep(2000);
    }
}
