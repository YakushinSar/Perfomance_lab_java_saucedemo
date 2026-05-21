package homework10.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
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
        log.info("Waiting for Cart page to load");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_LOADED_INDICATOR));
        return this;
    }

    @Step("Открытие страницы CartPage")
    public CartPage openPage() {
        log.info("Opening Cart page");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    // в корзине есть хотя бы один товар
    public boolean isCartNotEmpty() {
        List<WebElement> items = driver.findElements(CART_ITEMS);
        return !items.isEmpty();
    }

    @Step("Удаление первого товара из корзины")
    public CartPage removeFirstItem() {
        driver.findElement(REMOVE_BUTTON).click();
        return this;
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
        boolean matches = actualPrice.equals(expectedPrice);
        log.info("Price comparison - expected: '{}', actual: '{}', matches: {}", expectedPrice, actualPrice, matches);
        return matches;
    }

    // Переход назад в каталог
    @Step("Клик на Продолжить покупку")
    public ProductsPage backToCatalog() {
        log.info("Clicking 'Continue Shopping' button");
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
        return new ProductsPage(driver);
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
    public CheckoutInfoPage clickCheckoutButton() {
        log.info("Clicking 'Checkout' button");
        driver.findElement(CHECKOUT_BUTTON).click();
        return new CheckoutInfoPage(driver);
    }
}

