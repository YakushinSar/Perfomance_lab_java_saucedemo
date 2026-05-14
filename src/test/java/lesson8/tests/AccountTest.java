package lesson8.tests;

import lesson8.wrappers.AdressTextArea;
import lesson8.wrappers.Input;
import lesson8.wrappers.Select;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class AccountTest extends BaseTest {
    @Test
    public void checkAddNewAccount() throws InterruptedException {
        driver.get("https://demo.suiteondemand.com/index.php?module=Users&action=Login");
        driver.findElement(By.id("user_name")).sendKeys("will");
        driver.findElement(By.id("username_password")).sendKeys("will");
        driver.findElement(By.name("Login")).click();
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");

        //      заполняем инпуты данными, получается поля тут одинаковые - инпуты, у них одинаковое имя поля. раньше так делали
//        driver.findElement(By.id("name")).sendKeys("test");
//        driver.findElement(By.id("phone_office")).sendKeys("123456789");
//        sleep(2000);

//        проверка инпутов
        sleep(1000);
        new Input(driver, "Name").write("test");
        sleep(1000);
        new Input(driver, "Office Phone").write("12345");
        sleep(1000);
        new Input(driver, "Fax").write("9998877");
        sleep(1000);
        new Input(driver, "Website").write("1@mail.ru");
        sleep(1000);

//        проверка текстареа
        new AdressTextArea(driver, "Billing Address", "Street").write("test");
        sleep(1000);
        new AdressTextArea(driver, "Shipping Address", "Street").write("test");
        sleep(1000);

//      работа с выпадающим списком
        new Select(driver, "Type").select("Investor");
        sleep(1000);
        new Select(driver, "Industry").select("Banking");
        sleep(1000);

        driver.quit();
    }
}
