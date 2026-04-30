package homework5_TestNG.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {

    //предусловие для попадания на страницу CartPage
    void transitionToProductPage() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(description = "Проверка, что список товаров отображается на странице",
            testName = "Список товаров",
            priority = 1)
    public void productsListDisplayed() {
        transitionToProductPage();

        assertTrue(productsPage.isProductsListDisplayed());
    }

    @Test(description = "Проверка, что кнопка Add to cart добавляет товар в корзину",
            testName = "Добавление в корзину",
            priority = 2)
    public void addToCartButtonAddsProduct() {
        transitionToProductPage();
        productsPage.clickAddToCartButton(0);
        int cartCount = productsPage.getCartItemCount();
        assertEquals(cartCount, 1);
    }

    @Test(description = "Проверка, что сортировка товаров работает корректно",
            testName = "Сортировка товаров",
            priority = 3)
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