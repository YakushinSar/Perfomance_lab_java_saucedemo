package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutInfoTest extends BaseTest {

    //предусловие для попадания на страницу CheckoutInfoPage
    private void addCartToCheckoutInfo() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
        productsPage.clickCartBadge();
        cartPage.clickCheckoutButton();
    }

    //форма оформления заказа открывается
    @Test
    public void checkoutFormOpens() {
        addCartToCheckoutInfo();

        assertEquals(checkoutInfoPage.getTitle(), "Checkout: Your Information");
    }

    //поля First Name, Last Name, Zip/Postal Code отображаются
    @Test
    public void checkoutFormFieldsDisplayed() {
        addCartToCheckoutInfo();

        assertEquals(checkoutInfoPage.isALLFieldDisplayed(), true);
    }

    //отображается ошибка, если обязательные поля не заполнены, First Name
    @Test
    public void checkoutWithEmptyFieldsShowsError() throws InterruptedException {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData("", "Ivan", 12345);
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutInfoPage.getErrorMessage(), "Error: First Name is required");
    }

    //успешный переход на следующий шаг после заполнения формы.
    @Test
    public void successfulContinueAfterFillingForm() {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData("Ivanov", "Ivan", 12345);
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview");
    }
}
