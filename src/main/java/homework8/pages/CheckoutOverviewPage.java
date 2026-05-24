package homework8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutOverviewPage extends BasePage {

    private final By TITLE = By.xpath("//span[@class='title']");
    private final By FINISH_BUTTON = By.id("finish");
    private final By PAGE_LOADED_INDICATOR = By.xpath("//span[@class='title'][contains(text(), 'Overview')]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Загрузка страницы CheckoutOverviewPage")
    public CheckoutOverviewPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Клик на кнопку Finish")
    public CheckoutCompletePage clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
        return new CheckoutCompletePage(driver);
    }
}
