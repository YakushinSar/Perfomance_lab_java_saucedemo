package lesson10.wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Select {

    private final String SELECT_PATTERN = "//*[contains(text(), '%s')]/following-sibling::div//";
    WebDriver driver;
    String label;

    public Select(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void select(String option) {
//        делаю аннотацию логирования для метода
        log.info("Sekecting '{}' inside select '{}'", option, label);
        driver.findElement(By.xpath(String.format(SELECT_PATTERN + "option[contains(text(), '%s')]", label, option))).click();
    }
}

