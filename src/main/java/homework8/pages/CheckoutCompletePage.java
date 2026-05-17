package homework8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By TITLE = By.xpath("//span[@class='title']");
    private final By BACKHOME_BUTTON = By.id("back-to-products");
    private final By SUCCESS_MESSAGE = By.className("complete-header");
    private final By PAGE_LOADED_INDICATOR = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Загрузка страницы CheckoutCompletePage")
    public CheckoutCompletePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Клик на кнопку Назад в продукты")
    public ProductsPage clickBackHomeButton() {
        driver.findElement(BACKHOME_BUTTON).click();
        return new ProductsPage(driver);
    }

    public String getSuccessMessage() {
        return driver.findElement(SUCCESS_MESSAGE).getText();
    }
}
