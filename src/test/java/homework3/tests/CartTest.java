package homework3.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    //предусловие для попадания на страницу CartPage
    private void addProductToCart() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
    }

    //добавленный товар отображается в корзине
    @Test
    public void addedProductDisplayedInCart() {
        addProductToCart();
        productsPage.clickCartBadge(); // нужно добавить этот метод в ProductsPage
        assertTrue(cartPage.isCartNotEmpty(), "Корзина пуста");
    }

    //удаление товара из корзины работает корректно
    @Test
    public void removeFromCartWorks() {
        addProductToCart();
        productsPage.clickCartBadge();
        int beforeRemove = cartPage.getCartItemsCount();
        cartPage.removeFirstItem();
        int afterRemove = cartPage.getCartItemsCount();
        assertEquals(afterRemove, beforeRemove - 1, "Товар не удалился");
    }

    //цена товара в корзине совпадает с ценой в каталоге
    @Test
    public void cartPriceMatchesCatalogPrice() {
        addProductToCart();
        String catalogPrice = productsPage.getFirstProductPrice(); // нужно добавить в ProductsPage
        productsPage.clickCartBadge();
        assertTrue(cartPage.isPriceMatchesCatalog(catalogPrice), "Цены не совпадают");
    }

    //переход назад в каталог работает корректно
    @Test
    public void backToCatalogWorks() {
        addProductToCart();
        productsPage.clickCartBadge();
        cartPage.backToCatalog();
        assertTrue(cartPage.isOnProductsPage(), "Не вернулись на страницу товаров");
    }
}