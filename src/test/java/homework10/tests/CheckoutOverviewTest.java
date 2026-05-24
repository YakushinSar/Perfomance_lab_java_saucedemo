package homework10.tests;

import homework10.pages.LoginPage;
import homework10.utils.Retry;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
public class CheckoutOverviewTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutOverview
    private void addCheckoutInfoToCheckoutOverview() {
        new LoginPage(driver)
                .openPage()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .isPageOpened()
                .clickAddToCartButton(0)
                .clickAddToCartButton(1)
                .clickCartBadge()
                .isPageOpened()
                .clickCheckoutButton()
                .isPageOpened()
                .addData("Ivanov", "Ivan", "12345")
                .clickContinueButton();
    }

    @Test(description = "Проверка, что кнопка Finish завершает заказ",
            testName = "Завершение заказа")
    @Description("Проверка, что кнопка Finish завершает заказ")
    @Feature("CheckoutOverview in SauseDemo")
    @Story("Завершение заказа")
    @TmsLink("ID-14")
    @Issue("ID-14")
    @Owner("Якушин")
    public void finishButtonCompletesOrder() {
        addCheckoutInfoToCheckoutOverview();
        checkoutOverviewPage.clickFinishButton();

        assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!");
    }
}
