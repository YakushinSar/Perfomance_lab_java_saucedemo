package lesson5JUnit5.tests;


import lesson5JUnit5.pages.LoginPage;
import lesson5JUnit5.pages.ProductsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

@ExtendWith(CustomTestWatcher.class) // <-- Регистрируем TestWatcher
public class BaseTest {

    // ✅ Сделать ThreadLocal
    private static final ThreadLocal<LoginPage> loginPageLocal = new ThreadLocal<>();
    private static final ThreadLocal<ProductsPage> productsPageLocal = new ThreadLocal<>();

    // Удобные геттеры
    protected LoginPage getLoginPage() {
        return loginPageLocal.get();
    }

    protected ProductsPage getProductsPage() {
        return productsPageLocal.get();
    }


    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);

        DriverManager.setDriver(driver);

        // ✅ Каждый поток создаёт свои экземпляры страниц
        loginPageLocal.set(new LoginPage(DriverManager.getDriver()));
        productsPageLocal.set(new ProductsPage(DriverManager.getDriver()));
    }

    //    Выполнить после каждого теста, выполнить ДАЖЕ если тест упал/пропущен
    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
        // ✅ Очищаем ThreadLocal после теста
        loginPageLocal.remove();
        productsPageLocal.remove();
    }
}
