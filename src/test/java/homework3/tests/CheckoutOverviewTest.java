package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutOverviewTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutOverview
    private void addCheckoutInfoToCheckoutOverview() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.clickCheckoutButton();
        checkoutInfoPage.addData("Ivanov", "Ivan", 12345);
        checkoutInfoPage.clickContinueButton();
    }

    //кнопка Finish завершает заказ.
    @Test
    public void finishButtonCompletesOrder() {
        addCheckoutInfoToCheckoutOverview();
        checkoutOverviewPage.clickFinishButton();

        assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!");
    }
}
