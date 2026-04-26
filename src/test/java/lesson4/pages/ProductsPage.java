package lesson4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ProductsPage extends BasePage {
    private final By TITLE = By.cssSelector("[data-test=title]");  // 1 usage

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    //  получаем коллекцию товаров и выводим название товаров
    public void getProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("inventory-item-name"));

        for (WebElement product : products) {
            System.out.println(product.getText());
        }
    }

    //  если элемент содержит название определенное, то кладем его в корзину
    public void clickProducts(String text) {
        List<WebElement> products = driver.findElements(By.cssSelector("[data-test='inventory-item-name']"));
        WebElement neededProduct = null; // это нужный нам продукт

        for (WebElement product : products) {
            if (product.getText().contains(text)) {// text из параметра метода String text
                neededProduct = product;
                break;
            }
        }
        neededProduct.click();
    }

    // обращение к конкретному индексу коллекции
    public void indexProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("inventory-item-name"));
        products.get(0).getText();
    }

    // проверка пустая ли коллекция
    public void emptyProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("inventory-item-name"));
        Assert.assertTrue(products.isEmpty());
    }
}
