package homework8.tests;

import homework8.utils.Retry;
import io.qameta.allure.*;
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
        checkoutOverviewPage.isPageOpened();
        checkoutOverviewPage.clickFinishButton();
    }

    @Test(description = "Проверка, что отображается сообщение об успешном заказе",
            testName = "Сообщение об успехе",
            priority = 1)
    @Description("Проверка, что отображается сообщение об успешном заказе")
    @Feature("CheckoutComplete in SauseDemo")
    @Story("Сообщение об успехе")
    @TmsLink("ID-15")
    @Issue("ID-15")
    @Owner("Якушин")
    public void successMessageDisplayed() {
        addCheckoutOverviewToCheckoutComplete();
        checkoutCompletePage.isPageOpened();
        String actualMessage = checkoutCompletePage.getSuccessMessage();

        assertEquals(actualMessage, "Thank you for your order!");
    }

    @Test(description = "Проверка, что кнопка Back Home возвращает в каталог",
            testName = "Возврат в каталог",
            priority = 2)
    @Description("Проверка, что кнопка Back Home возвращает в каталог")
    @Feature("CheckoutComplete in SauseDemo")
    @Story("Возврат в каталог товаров")
    @TmsLink("ID-16")
    @Issue("ID-16")
    @Owner("Якушин")
    public void backHomeButtonReturnsToCatalog() {
        addCheckoutOverviewToCheckoutComplete();
        checkoutCompletePage.clickBackHomeButton();

        assertTrue(productsPage.isProductsListDisplayed());
    }
}


