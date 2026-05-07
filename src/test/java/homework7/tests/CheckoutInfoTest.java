package homework7.tests;

import homework7.utils.Retry;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test(retryAnalyzer = Retry.class)
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
            testName = "Открытие формы заказа",
            priority = 1)
    @Description("Проверка, что форма оформления заказа открывается")
    @Feature("CheckoutInfo in SauseDemo")
    @Story("Открытие формы заказа")
    @TmsLink("ID-10")
    @Issue("ID-10")
    @Owner("Якушин")
    public void checkoutFormOpens() {
        addCartToCheckoutInfo();

        assertEquals(checkoutInfoPage.getTitle(), "Checkout: Your Information");
    }

    @Test(description = "Проверка, что поля формы отображаются",
            testName = "Поля формы",
            priority = 2)
    @Description("Проверка, что поля формы отображаются")
    @Feature("CheckoutInfo in SauseDemo")
    @Story("Проверка полей формы")
    @TmsLink("ID-11")
    @Issue("ID-11")
    @Owner("Якушин")
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
    @Description("Проверка, что при пустых полях отображается соответствующая ошибка")
    @Feature("CheckoutInfo in SauseDemo")
    @Story("При пустых полях отображается соответствующая ошибка")
    @TmsLink("ID-12")
    @Issue("ID-12")
    @Owner("Якушин")
    public void newTest(String firstName, String lastName, String postalCode, String expectedError) {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData(firstName, lastName, postalCode);
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutInfoPage.getErrorMessage(), expectedError);
    }

    @Test(description = "Проверка, что после заполнения формы происходит переход на следующий шаг",
            testName = "Переход на следующий шаг",
            priority = 4)
    @Description("Проверка, что после заполнения формы происходит переход на следующий шаг")
    @Feature("CheckoutInfo in SauseDemo")
    @Story("Переход на CheckoutOverview")
    @TmsLink("ID-13")
    @Issue("ID-13")
    @Owner("Якушин")
    public void successfulContinueAfterFillingForm() {
        addCartToCheckoutInfo();
        checkoutInfoPage.addData("Ivanov", "Ivan", "12345");
        checkoutInfoPage.clickContinueButton();

        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview");
    }
}
