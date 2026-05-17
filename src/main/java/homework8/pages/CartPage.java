package homework8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    private final By CART_ITEMS = By.className("cart_item");
    private final By CART_ITEM_NAMES = By.className("inventory_item_name");
    private final By CART_ITEM_PRICES = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By PAGE_LOADED_INDICATOR = By.className("cart_list");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Загрузка страницы CartPage")
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    @Step("Открытие страницы CartPage")
    public void openPage() {
        driver.get(BASE_URL + "/cart.html");
    }

    // в корзине есть хотя бы один товар
    public boolean isCartNotEmpty() {
        List<WebElement> items = driver.findElements(CART_ITEMS);
        return !items.isEmpty();
    }

    // Удаление товара из корзины
    @Step("Удаление товара из корзины")
    public void removeFirstItem() {
        driver.findElement(REMOVE_BUTTON).click();
    }

    // Получение количества товаров в корзине
    public int getCartItemsCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    // Получение цены товара в корзине (первого)
    public String getFirstItemPriceInCart() {
        return driver.findElement(CART_ITEM_PRICES).getText();
    }

    // цена в корзине совпадает с ценой в каталоге
    public boolean isPriceMatchesCatalog(String expectedPrice) {
        String actualPrice = getFirstItemPriceInCart();
        return actualPrice.equals(expectedPrice);
    }

    // Переход назад в каталог
    @Step("Клик на Продолжить покупку")
    public void backToCatalog() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    // вернулись на страницу товаров
    @Step("Возврат на страницу товаров")
    public boolean isOnProductsPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    // Переход к оформлению заказа
    @Step("Переход на  CheckoutInfoPage")
    public void proceedToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    //    Клик на кнопку Checkout
    @Step("Клик на кнопку Checkout")
    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}

