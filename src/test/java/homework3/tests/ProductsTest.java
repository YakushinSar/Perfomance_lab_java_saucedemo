package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {

    //предусловие для попадания на страницу CartPage
    void transitionToProductPage() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
    }

    //отображается список товаров
    @Test
    public void productsListDisplayed() {
        transitionToProductPage();

        assertTrue(productsPage.isProductsListDisplayed());
    }

    //кнопка Add to cart добавляет товар в корзину
    @Test
    public void addToCartButtonAddsProduct() {
        transitionToProductPage();
        productsPage.clickAddToCartButton(0);
        int cartCount = productsPage.getCartItemCount();
        assertEquals(cartCount, 1);
    }

    //сортировка товаров работает корректно.
    @Test
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