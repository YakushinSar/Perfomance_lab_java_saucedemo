package homework8.tests;

import homework8.utils.Retry;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Test(retryAnalyzer = Retry.class)
public class ProductsTest extends BaseTest {

    //предусловие для попадания на страницу CartPage
    void transitionToProductPage() {
        loginPage.openPage();
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageOpened();
    }

    @Test(description = "Проверка, что список товаров отображается на странице",
            testName = "Список товаров",
            priority = 1)
    @Description("Проверка, что список товаров отображается на странице")
    @Feature("Product in SauseDemo")
    @Story("Список товаров")
    @TmsLink("ID-3")
    @Issue("ID-3")
    @Owner("Якушин")
    public void productsListDisplayed() {
        transitionToProductPage();

        assertTrue(productsPage.isProductsListDisplayed());
    }

    @Test(description = "Проверка, что кнопка Add to cart добавляет товар в корзину",
            testName = "Добавление в корзину",
            priority = 2)
    @Description("Проверка, что кнопка Add to cart добавляет товар в корзину")
    @Feature("Product in SauseDemo")
    @Story("Добавление в корзину")
    @TmsLink("ID-4")
    @Issue("ID-4")
    @Owner("Якушин")
    public void addToCartButtonAddsProduct() {
        transitionToProductPage();
        productsPage.clickAddToCartButton(0);
        int cartCount = productsPage.getCartItemCount();
        assertEquals(cartCount, 1);
    }

    @Test(description = "Проверка, что кнопка Add to cart добавляет товар в корзину по имени товара",
            testName = "Добавление в корзину",
            priority = 2)
    @Description("Проверка, что кнопка Add to cart добавляет товар в корзину по имени товара")
    @Feature("Product in SauseDemo")
    @Story("Добавление в корзину")
    @TmsLink("ID-4_1")
    @Issue("ID-4_1")
    @Owner("Якушин")
    public void addToCartButtonAddsProductNew() {
        transitionToProductPage();
        productsPage.addToCartNew("Test.allTheThings() T-Shirt (Red)"); //"Test.allTheThings() T-Shirt (Red)" это название товара с фронта
    }


    @Test(description = "Проверка, что сортировка товаров работает корректно",
            testName = "Сортировка товаров",
            priority = 3)
    @Description("Проверка, что сортировка товаров работает корректно")
    @Feature("Product in SauseDemo")
    @Story("Сортировка товаров")
    @TmsLink("ID-5")
    @Issue("ID-5")
    @Owner("Якушин")
    public void productSortingWorks() {
        transitionToProductPage();
        //сортировка A → Z (по умолчанию)
        productsPage.sortProductsByNameAZ();
        String firstNameAZ = productsPage.getFirstProductName();
        //сортировка Z → A
        productsPage.sortProductsByNameZA();
        String firstNameZA = productsPage.getFirstProductName();

        assertNotEquals(firstNameAZ, firstNameZA);
    }
}