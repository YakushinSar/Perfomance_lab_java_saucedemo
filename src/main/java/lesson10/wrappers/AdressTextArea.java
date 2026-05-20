package lesson10.wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
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
        //        делаю аннотацию логирования для метода
        log.info("Writing '{}' in to '{}' in address '{}'", text, label, addressLabel);
        driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]/ancestor::" +
                "div[contains(@class, 'edit-view-row-item')]//*[contains(text(), '%s')]/ancestor::" +
                "tr//textarea", addressLabel, label))).sendKeys(text);
    }
}
