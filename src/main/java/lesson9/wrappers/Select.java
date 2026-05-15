package lesson9.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Select {
    private final String SELECT_PATTERN = "//*[contains(text(), '%s')]/following-sibling::div//";
    WebDriver driver;
    String label;

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {

        driver.findElement(By.xpath(String.format(SELECT_PATTERN + "option[contains(text(), '%s')]", label, option))).click();
    }
}

