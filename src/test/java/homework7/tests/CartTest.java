package homework7.tests;

import homework7.utils.Retry;
import io.qameta.allure.*;
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
    @Description("Проверка, что добавленный товар отображается в корзине")
    @Feature("Cart in SauseDemo")
    @Story("Товар в корзине")
    @TmsLink("ID-6")
    @Issue("ID-6")
    @Owner("Якушин")
    public void addedProductDisplayedInCart() {
        addProductToCart();
        productsPage.clickCartBadge(); // нужно добавить этот метод в ProductsPage
        assertTrue(cartPage.isCartNotEmpty(), "Корзина пуста");
    }

    @Test(description = "Проверка, что удаление товара из корзины работает корректно",
            testName = "Удаление из корзины",
            priority = 2)
    @Description("Проверка, что удаление товара из корзины работает корректно")
    @Feature("Cart in SauseDemo")
    @Story("Удаление из корзины")
    @TmsLink("ID-7")
    @Issue("ID-7")
    @Owner("Якушин")
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
    @Description("Проверка, что цена товара в корзине совпадает с ценой в каталоге")
    @Feature("Cart in SauseDemo")
    @Story("Цена в корзине")
    @TmsLink("ID-8")
    @Issue("ID-8")
    @Owner("Якушин")
    public void cartPriceMatchesCatalogPrice() {
        addProductToCart();
        String catalogPrice = productsPage.getFirstProductPrice(); // нужно добавить в ProductsPage
        productsPage.clickCartBadge();
        assertTrue(cartPage.isPriceMatchesCatalog(catalogPrice), "Цены не совпадают");
    }

    @Test(description = "Проверка, что переход назад в каталог работает корректно",
            testName = "Возврат в каталог",
            priority = 4)
    @Description("Проверка, что переход назад в каталог работает корректно")
    @Feature("Cart in SauseDemo")
    @Story("Возврат в каталог")
    @TmsLink("ID-9")
    @Issue("ID-9")
    @Owner("Якушин")
    public void backToCatalogWorks() {
        addProductToCart();
        productsPage.clickCartBadge();
        cartPage.backToCatalog();
        assertTrue(cartPage.isOnProductsPage(), "Не вернулись на страницу товаров");
    }
}