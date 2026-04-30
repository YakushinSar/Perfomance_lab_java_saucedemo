package lesson5;

public class SuiteTestNG {
    /*
# В TestNG есть 5 сущностей:
- Method - один тестовый метод, помеченный аннотацией @Test
- Class - класс, который содержит в себе тестовые методы, LoginTest
- Test - это тестовый набор
- Group - группа тестов (тестовых наборов)
- Suite - весь пакет тестов. По-сути .xml файл, созданный для TestNG

# Файлов с конфигрурацией TestNG xml file может быть больше количество для разных случаев. Они нужны для:
1. Разделение тестов на группы с возможностью запуска отдельно. Допустим: SmokeTest.xml,RegressionTest.xml
2. Возможность запуска в параллели.
3. Возможность запуска из командной строки.

# Уровни параллелизации в TestNG
parallel определяет, что именно будет запускаться параллельно. Возможные значения:

Значение	            Что запускается параллельно	     Пример использования
parallel="methods"	    Отдельные тестовые методы	     Когда методы не зависят друг от друга
parallel="classes"	    Разные тестовые классы	         Разные классы тестов в одном <test>
parallel="tests"	    Разные блоки <test>	             Кросс-браузерное тестирование
parallel="instances"	Разные экземпляры классов	     При параметризованных тестах

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<!-- thread-count количество параллельных потоков, если не указан, TestNG использует значение по умолчанию (5). Способ распараллеливания parallel (методы, классы, тесты) 5 сущностей -->
<suite thread-count="2"
       name="Sauce demo"
       parallel="classes">

    <!-- это тестовые наборы, их может быть несколько -->
    <test name="Поток 1">
        <!-- тут запустятся только тесты помеченные группой "smoke" -->
        <groups>
            <run>
                <include name="smoke"/>
            </run>
        </groups>
        <classes>
            <!-- сюда в кавычки ставятся тестовые классы которые надо запускать -->
            <class name=""/>
        </classes>
        <parameter name="password" value="password"/>
        <parameter name="email" value="test@mailinator.com"/>
    </test>
</suite>

# Считывание параметров извне
1. @Parameters - дает возможность считать переменные из .xml файла
2. @Optional - подставляет дефолтное значение в случае если данные не будут найдены в xml

# Пример для кроссбраузерного тестирования
Файл для вкладки resourses: testng.xml oпределяет браузеры для запуска
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Sauce Demo Cross Browser" parallel="tests">

    <test name="Chrome">
        <parameter name="browser" value="chrome"/>
        <classes>
            <class name="lesson5.tests.LoginTest"/>
        </classes>
    </test>

    <test name="Firefox">
        <parameter name="browser" value="firefox"/>
        <classes>
            <class name="lesson5.tests.LoginTest"/>
        </classes>
    </test>

</suite>

Файл для вкладки BaseTest: BaseTest.setup() инициализирует драйвер в зависимости от параметра
@Parameters({"browser"})
@BeforeMethod(alwaysRun = true)
public void setup(@Optional("chrome") String browser) {
    WebDriver driver;

    if (browser.equalsIgnoreCase("chrome")) {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        driver = new ChromeDriver(options);
    } else if (browser.equalsIgnoreCase("firefox")) {
        driver = new FirefoxDriver();
    } else {
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    driver.manage().window().maximize();
    DriverManager.setDriver(driver);

    loginPage = new LoginPage(driver);
    productsPage = new ProductsPage(driver);
}

Как запустить:
1. Из IDEA:
ПКМ на src/test/resources/testng.xml → Run
2. Из консоли:
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml, где
- mvn clean test Очищает проект и запускает тесты
- -Dsurefire.suiteXmlFiles=... Указывает Maven Surefire Plugin на файл testng.xml
- src/test/resources/testng.xml это путь к файлу конфигурации TestNG в Maven-проекте
Примеры команд запуска из консоли: меняем только конечную папку конфигурации
Запуск smoke-тестов
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/smoke.xml
Запуск регрессионных тестов
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/regression.xml
Запуск кросс-браузерных тестов
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/crossBrowser.xml


     */
}
