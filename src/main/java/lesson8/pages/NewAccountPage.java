package lesson8.pages;

import lesson8.wrappers.AdressTextArea;
import lesson8.wrappers.Input;
import lesson8.wrappers.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NewAccountPage {
    WebDriver driver;

    public NewAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //   метод для создания нового аккаунта на странице NewAccountPage
    public void addNewAccount(String name, String phone, String fax, String website, String street, String type, String industry) {
//        проверка инпутов
        new Input(driver, "Name").write(name);
        new Input(driver, "Office Phone").write(phone);
        new Input(driver, "Fax").write(fax);
        new Input(driver, "Website").write(website);

//        проверка текстареа
        new AdressTextArea(driver, "Billing Address", "Street").write(street);
        new AdressTextArea(driver, "Shipping Address", "Street").write(street);

//      работа с выпадающим списком
        new Select(driver, "Type").select(type);
        new Select(driver, "Industry").select(industry);
    }

    // метод для клика на кнопку SAVE
    public void clickSave() {
        driver.findElement(By.id("SAVE")).click();
    }
}
