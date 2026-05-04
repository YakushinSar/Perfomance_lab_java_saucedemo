package homework5_TestNG.tests;

import homework5_TestNG.utils.Retry;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(retryAnalyzer = Retry.class)
public class CartTest extends BaseTest {

    //предусловие для попадания на страницу CartPage
    private void addProductToCart() {
        loginPage.openPage();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.clickAddToCartButton(0);
        productsPage.clickAddToCartButton(1);
    }

    @Test(description = "Проверка, что добавленный товар отображается в корзине",
            testName = "Товар в корзине",
            priority = 1)
    public void addedProductDisplayedInCart() {
        addProductToCart();
        productsPage.clickCartBadge(); // нужно добавить этот метод в ProductsPage
        assertTrue(cartPage.isCartNotEmpty(), "Корзина пуста");
    }

    @Test(description = "Проверка, что удаление товара из корзины работает корректно",
            testName = "Удаление из корзины",
            priority = 2)
    public void removeFromCartWorks() {
        addProductToCart();
        productsPage.clickCartBadge();
        int beforeRemove = cartPage.getCartItemsCount();
        cartPage.removeFirstItem();
        int afterRemove = cartPage.getCartItemsCount();
        assertEquals(afterRemove, beforeRemove - 1, "Товар не удалился");
    }

    @Test(description = "Проверка, что цена товара в корзине совпадает с ценой в каталоге",
            testName = "Цена в корзине",
            priority = 3)
    public void cartPriceMatchesCatalogPrice() {
        addProductToCart();
        String catalogPrice = productsPage.getFirstProductPrice(); // нужно добавить в ProductsPage
        productsPage.clickCartBadge();
        assertTrue(cartPage.isPriceMatchesCatalog(catalogPrice), "Цены не совпадают");
    }

    @Test(description = "Проверка, что переход назад в каталог работает корректно",
            testName = "Возврат в каталог",
            priority = 4)
    public void backToCatalogWorks() {
        addProductToCart();
        productsPage.clickCartBadge();
        cartPage.backToCatalog();
        assertTrue(cartPage.isOnProductsPage(), "Не вернулись на страницу товаров");
    }
}