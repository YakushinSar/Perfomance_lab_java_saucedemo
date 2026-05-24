package lesson8.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test='title']");
    // в локаторе мы сначала поднимаемся к родителю карточки товара, а потом спускаемся к кнопке "Добавить товар"
    //  Sauce Labs Backpack меняем на подстановочное значение названия %s
//    private final String ADD_TO_CART_PATTERN = "//*[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы ProductsPage")
    public void open() {
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return driver.findElement(TITLE).getText();
    }

    //  получаем коллекцию товаров и выводим название товаров
    public void getProducts() {
        List<WebElement> products = driver.findElements(By.cssSelector("inventory-item-name"));

        for (WebElement product : products) {
            System.out.println(product.getText());
        }
    }

//    // проверка пустая ли коллекция
//    public void emptyProducts() {
//        List<WebElement> products = driver.findElements(By.cssSelector("inventory-item-name"));
//        Assert.assertTrue(products.isEmpty());
//    }

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

    // новый!!! метод добавляющий товар в корзину в зависимости от названия товара, привязываемся к названию
    //  String.format это метод, позволяющий вместо переменной подставлять любое значение вместо %s.
    public void addToCartNew(String productName) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, productName))).click();
    }

    /*
    использование метода в тесте
    public void checkLoginWithEmptyPassword() {
        LoginPage.open();
        LoginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Test.allTheThings() T-Shirt (Red)"); //"Test.allTheThings() T-Shirt (Red)" это название товара с фронта
    }
         */

}
