package homework3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInfoPage extends BasePage {
    private final By FIRSTNAME_FIELD = By.name("firstName");
    private final By LASTNAME_FIELD = By.name("lastName");
    private final By POSTAL_CODE = By.name("postalCode");
    private final By CONTINUE_BUTTON = By.name("continue");
    private final By TITLE = By.xpath("//span[@class='title']");
    private final By ERROR_MESSAGE = By.xpath("//*[contains(text(), 'Error: First Name is required')]");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void addData(String firstName, String lastName, int postalCode) {
        driver.findElement(FIRSTNAME_FIELD).sendKeys(firstName);
        driver.findElement(LASTNAME_FIELD).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(Integer.toString(postalCode));
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public boolean isALLFieldDisplayed() {
        return driver.findElement(FIRSTNAME_FIELD).isDisplayed() && driver.findElement(LASTNAME_FIELD).isDisplayed() && driver.findElement(POSTAL_CODE).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
