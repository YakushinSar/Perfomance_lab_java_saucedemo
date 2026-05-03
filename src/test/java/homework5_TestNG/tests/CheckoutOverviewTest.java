package homework5_TestNG.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
public class CheckoutOverviewTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutOverview
    private void addCheckoutInfoToCheckoutOverview() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.clickCheckoutButton();
        checkoutInfoPage.addData("Ivanov", "Ivan", "12345");
        checkoutInfoPage.clickContinueButton();
    }

    @Test(description = "Проверка, что кнопка Finish завершает заказ",
            testName = "Завершение заказа")
    public void finishButtonCompletesOrder() {
        addCheckoutInfoToCheckoutOverview();
        checkoutOverviewPage.clickFinishButton();

        assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!");
    }
}
