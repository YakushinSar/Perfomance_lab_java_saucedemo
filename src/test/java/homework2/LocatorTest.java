package homework2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;


public class LocatorTest {
    @Test
    public void checkLocator() {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@id='login-button']")).click();

        driver.findElement(By.linkText("Twitter"));
        driver.findElement(By.partialLinkText("Linked"));
        driver.findElement(By.xpath("//div[text()='Swag Labs']"));
        driver.findElement(By.xpath("//button[contains(@id, 'add-to-cart')]"));
        driver.findElement(By.xpath("//*[contains(text(), 'cart')]"));
        driver.findElement(By.xpath("//button[text()='Add to cart']//ancestor::div[@class='inventory_item']"));
        driver.findElement(By.xpath("//div[@class='inventory_item']//descendant::button"));
        driver.findElement(By.xpath("//div[@class='app_logo']/following::button"));
        driver.findElement(By.xpath("//button[text()='Add to cart']/parent::div"));
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']/preceding::div"));

        driver.findElement(By.cssSelector(".app_logo"));
        driver.findElement(By.cssSelector(".btn.btn_primary"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("button.btn_inventory"));
        driver.findElement(By.cssSelector("[id^='add-to-cart']"));
        driver.findElement(By.cssSelector("[class*='inventory']"));

        driver.quit();
    }
}
