package homework5_TestNG.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(retryAnalyzer = Retry.class)
public class CheckoutCompleteTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutInfoPage
    private void addCheckoutOverviewToCheckoutComplete() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.clickCheckoutButton();
        checkoutInfoPage.addData("Ivanov", "Ivan", "12345");
        checkoutInfoPage.clickContinueButton();
        checkoutOverviewPage.clickFinishButton();
    }

    @Test(description = "Проверка, что отображается сообщение об успешном заказе",
            testName = "Сообщение об успехе",
            priority = 1)
    public void successMessageDisplayed() {
        addCheckoutOverviewToCheckoutComplete();
        String actualMessage = checkoutCompletePage.getSuccessMessage();

        assertEquals(actualMessage, "Thank you for your order!");
    }

    @Test(description = "Проверка, что кнопка Back Home возвращает в каталог",
            testName = "Возврат в каталог",
            priority = 2)
    public void backHomeButtonReturnsToCatalog() {
        addCheckoutOverviewToCheckoutComplete();
        checkoutCompletePage.clickBackHomeButton();

        assertTrue(productsPage.isProductsListDisplayed());
    }
}


