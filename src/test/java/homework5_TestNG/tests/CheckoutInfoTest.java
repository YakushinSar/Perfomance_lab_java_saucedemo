package homework5_TestNG.tests;

import org.testng.annotations.DataProvider;
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

    @Test(description = "Проверка, что форма оформления заказа открывается",
            testName = "Открытие формы",
            priority = 1)
    public void checkoutFormOpens() {
        addCartToCheckoutInfo();

        assertEquals(checkoutInfoPage.getTitle(), "Checkout: Your Information");
    }

    @Test(description = "Проверка, что поля формы отображаются",
            testName = "Поля формы",
            priority = 2)
    public void checkoutFormFieldsDisplayed() {
        addCartToCheckoutInfo();

        assertEquals(checkoutInfoPage.isALLFieldDisplayed(), true);
    }

    @DataProvider(name = "EmptyFieldsData")
    public Object[][] emptyFieldsData() {
        return new Object[][]{
                {"", "Ivan", "12345", "Error: First Name is required"},
                {"Ivanov", "", "12345", "Error: Last Name is required"},
                {"Ivanov", "Ivan", "", "Error: Postal Code is required"}
        };
    }

    @Test(dataProvider = "EmptyFieldsData",
            description = "Проверка, что при пустых полях отображается соответствующая ошибка",
            testName = "Ошибка валидации, параметризированный",
            priority = 3)
    public void newTest(String firstName, String lastName, String postalCode, String expectedError) {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData(firstName, lastName, postalCode);
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutInfoPage.getErrorMessage(), expectedError);
    }

    @Test(description = "Проверка, что после заполнения формы происходит переход на следующий шаг",
            testName = "Переход на следующий шаг",
            priority = 4)
    public void successfulContinueAfterFillingForm() {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData("Ivanov", "Ivan", "12345");
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview");
    }
}
