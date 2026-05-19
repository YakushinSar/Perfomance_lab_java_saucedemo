package lesson10.pages;

import lesson10.dto.Account;
import lesson10.wrappers.AdressTextArea;
import lesson10.wrappers.Input;
import lesson10.wrappers.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountPage {
    WebDriver driver;

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://demo.suiteondemand.com/index.php?module=Accounts&action=EditView&return_module=Accounts&return_action=DetailView");

    }

    //  Value Object/DTO, dto передается в качестве параметров метода (Account account)
    public void addNewAccount(Account account) {
//        проверка инпутов
        new Input(driver, "Name").write(account.getName());
        new Input(driver, "Office Phone").write(account.getPhone());
        new Input(driver, "Fax").write(account.getFax());
        new Input(driver, "Website").write(account.getWebsite());

//        проверка текстареа
        new AdressTextArea(driver, "Billing Address", "Street").write(account.getStreet());
        new AdressTextArea(driver, "Shipping Address", "Street").write(account.getStreet());

//      работа с выпадающим списком
        new Select(driver, "Type").select(account.getType());
        new Select(driver, "Industry").select(account.getIndustry());
    }

    // метод для клика на кнопку SAVE
    public void clickSave() {
        driver.findElement(By.id("SAVE")).click();
    }
}
