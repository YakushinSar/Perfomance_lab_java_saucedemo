package lesson6.tests;


import lesson6.pages.LoginPage;
import lesson6.pages.ProductsPage;
import lesson6.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.util.HashMap;

@Listeners(TestListener.class)
public class BaseTest {

    // ✅ Сделать ThreadLocal
    private static final ThreadLocal<LoginPage> loginPageLocal = new ThreadLocal<>();
    private static final ThreadLocal<ProductsPage> productsPageLocal = new ThreadLocal<>();

    // геттеры
    protected LoginPage getLoginPage() {
        return loginPageLocal.get();
    }

    protected ProductsPage getProductsPage() {
        return productsPageLocal.get();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
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

        // Каждый поток создаёт свои экземпляры страниц
        loginPageLocal.set(new LoginPage(DriverManager.getDriver()));
        productsPageLocal.set(new ProductsPage(DriverManager.getDriver()));
    }

    //  Выполнить после каждого теста, выполнить ДАЖЕ если тест упал/пропущен
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
        // Очищаем ThreadLocal после теста
        loginPageLocal.remove();
        productsPageLocal.remove();
    }
}
