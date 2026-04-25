package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CheckoutCompleteTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutInfoPage
    private void addCheckoutOverviewToCheckoutComplete() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.clickCheckoutButton();
        checkoutInfoPage.addData("Ivanov", "Ivan", 12345);
        checkoutInfoPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
    }

    //Отображается сообщение об успешном заказе
    @Test
    public void successMessageDisplayed() {
        addCheckoutOverviewToCheckoutComplete();
        String actualMessage = checkoutCompletePage.getSuccessMessage();

        assertEquals(actualMessage, "Thank you for your order!");
    }

    //Кнопка `Back Home` возвращает в каталог.
    @Test
    public void backHomeButtonReturnsToCatalog() {
        addCheckoutOverviewToCheckoutComplete();
        checkoutCompletePage.clickBackHomeButton();

        assertTrue(productsPage.isProductsListDisplayed());
    }
}


