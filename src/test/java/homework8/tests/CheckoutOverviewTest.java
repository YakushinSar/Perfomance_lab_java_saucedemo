package homework8.tests;

import homework8.utils.Retry;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
public class CheckoutOverviewTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutOverview
    private void addCheckoutInfoToCheckoutOverview() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageOpened();
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.isPageOpened();
        cartPage.clickCheckoutButton();
        checkoutInfoPage.isPageOpened();
        checkoutInfoPage.addData("Ivanov", "Ivan", "12345");
        checkoutInfoPage.clickContinueButton();
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
