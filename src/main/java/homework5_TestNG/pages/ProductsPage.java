package homework5_TestNG.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.xpath("//span[@class='title']");
    private final By PRODUCT_LIST = By.className("inventory_list");
    private final By ADD_TO_CART_BUTTON = By.xpath("//button[text()='Add to cart']");
    private final By CART_BADGE = By.className("shopping_cart_badge");
    private final By SORT_DROPDOWN = By.className("product_sort_container");
    private final By PRODUCT_NAMES = By.className("inventory_item_name");
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(BASE_URL + "/inventory.html");
    }


    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    // 1. Проверка, что список товаров отображается
    public boolean isProductsListDisplayed() {
        return driver.findElement(PRODUCT_LIST).isDisplayed();
    }

    // 2. Нажать Add to cart на товаре по индексу
    public void clickAddToCartButton(int index) {
        List<WebElement> buttons = driver.findElements(ADD_TO_CART_BUTTON);
        buttons.get(index).click();
    }

    // 3. Получить количество товаров в корзине
    public int getCartItemCount() {
        try {
            String badgeText = driver.findElement(CART_BADGE).getText();
            return Integer.parseInt(badgeText);
        } catch (Exception e) {
            return 0;
        }
    }

    // 4. Сортировка A → Z
    public void sortProductsByNameAZ() {
        Select sortSelect = new Select(driver.findElement(SORT_DROPDOWN));
        sortSelect.selectByValue("az");
    }

    // 5. Сортировка Z → A
    public void sortProductsByNameZA() {
        Select sortSelect = new Select(driver.findElement(SORT_DROPDOWN));
        sortSelect.selectByValue("za");
    }

    // 6. Получить название первого товара в списке
    public String getFirstProductName() {
        return driver.findElements(PRODUCT_NAMES).get(0).getText();
    }

    // 7. Получить цену первого товара
    public String getFirstProductPrice() {
        return driver.findElement(By.className("inventory_item_price")).getText();
    }

    // 8. Кликнуть на иконку корзины
    public void clickCartBadge() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }
}
