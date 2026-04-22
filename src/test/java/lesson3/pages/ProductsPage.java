package lesson3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private final By TITLE = By.cssSelector("[data-test=title]");  // 1 usage

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }
    
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }
}
