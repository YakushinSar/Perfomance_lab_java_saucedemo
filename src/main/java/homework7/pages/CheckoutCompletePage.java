package homework7.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.xpath("//span[@class='title']");
    private final By BACKHOME_BUTTON = By.id("back-to-products");
    private final By SUCCESS_MESSAGE = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Клик на кнопку Назад в продукты")
    public void clickBackHomeButton() {
        driver.findElement(BACKHOME_BUTTON).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }
}
