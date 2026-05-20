package lesson10.wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class Input {

    WebDriver driver;
    //  label это название поля инпута, которое мы будем выбирать на странице
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    //    метод позволяющий заполнять любые инпуты различными текстовыми данными - передаваемого в параметрах text, а имя инпута подставляется в '%s'
    public void write(String text) {
        //        делаю аннотацию логирования для метода
        log.info("Writing '{}' in to '{}'", text, label);
        // driver.findElement(By.xpath("//div[contains(text(), 'Office Phone')]/parent::div//input")).sendKeys(text);
        driver.findElement(By.xpath(String.format("//div[contains(text(), '%s')]/parent::div//input", label))).sendKeys(text);
    }
}
