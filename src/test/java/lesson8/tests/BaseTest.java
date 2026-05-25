package lesson8.tests;


import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import lesson8.pages.LoginPage;
import lesson8.pages.ProductsPage;
import lesson8.utils.PropertyReader;
import lesson8.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import java.util.HashMap;

@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {

    // ✅ Сделать ThreadLocal
    private static final ThreadLocal<LoginPage> loginPageLocal = new ThreadLocal<>();
    private static final ThreadLocal<ProductsPage> productsPageLocal = new ThreadLocal<>();

    protected String user = System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password = System.getProperty("password", PropertyReader.getProperty("password"));

    // геттеры
    protected LoginPage getLoginPage() {
        return loginPageLocal.get();
    }

    protected ProductsPage getProductsPage() {
        return productsPageLocal.get();
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Открытие браузера")
    @Description("Настройка браузера")
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
    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    @Description("Закрытие браузера")
    public void tearDown() {
        DriverManager.quitDriver();
        // Очищаем ThreadLocal после теста
        loginPageLocal.remove();
        productsPageLocal.remove();
    }
}
