package homework3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By CART_ITEMS = By.className("cart_item");
    private final By CART_ITEM_NAMES = By.className("inventory_item_name");
    private final By CART_ITEM_PRICES = By.className("inventory_item_price");
    private final By REMOVE_BUTTON = By.xpath("//button[text()='Remove']");
    private final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");
    private final By CHECKOUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL + "/cart.html");
    }

    // в корзине есть хотя бы один товар
    public boolean isCartNotEmpty() {
        List<WebElement> items = driver.findElements(CART_ITEMS);
        return !items.isEmpty();
    }

    // Удаление товара из корзины
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
    public void backToCatalog() {
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    // вернулись на страницу товаров
    public boolean isOnProductsPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    // Переход к оформлению заказа
    public void proceedToCheckout() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    //    клик на кнопку Checkout
    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }
}

