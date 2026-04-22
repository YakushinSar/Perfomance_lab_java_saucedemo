package lesson3.tests;

import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");
        driver.quit();
    }
}
