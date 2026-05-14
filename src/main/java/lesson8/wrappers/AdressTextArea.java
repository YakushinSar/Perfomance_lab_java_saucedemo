package lesson8.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdressTextArea {
    WebDriver driver;
    String addressLabel;
    String label;

    public AdressTextArea(WebDriver driver, String addressLabel, String label) {
        this.driver = driver;
        this.addressLabel = addressLabel;
        this.label = label;
    }

    public void write(String text) {
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]/ancestor::" +
                "div[contains(@class, 'edit-view-row-item')]//*[contains(text(), '%s')]/ancestor::" +
                "tr//textarea", addressLabel, label))).sendKeys(text);
    }
}
