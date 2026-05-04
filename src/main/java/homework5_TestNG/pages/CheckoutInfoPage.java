package homework5_TestNG.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutInfoPage extends BasePage {

    private final By FIRSTNAME_FIELD = By.name("firstName");
    private final By LASTNAME_FIELD = By.name("lastName");
    private final By POSTAL_CODE = By.name("postalCode");
    private final By CONTINUE_BUTTON = By.name("continue");
    private final By TITLE = By.xpath("//span[@class='title']");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public void addData(String firstName, String lastName, String postalCode) {
        WebElement firstNameField = driver.findElement(FIRSTNAME_FIELD);
        WebElement lastNameField = driver.findElement(LASTNAME_FIELD);
        WebElement postalCodeField = driver.findElement(POSTAL_CODE);

        firstNameField.clear();
        lastNameField.clear();
        postalCodeField.clear();

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public boolean isALLFieldDisplayed() {
        return driver.findElement(FIRSTNAME_FIELD).isDisplayed() && driver.findElement(LASTNAME_FIELD).isDisplayed() && driver.findElement(POSTAL_CODE).isDisplayed();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
    }

    public void clickContinueButton() {
        driver.findElement(CONTINUE_BUTTON).click();
    }
}
