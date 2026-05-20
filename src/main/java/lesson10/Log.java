package lesson10;

public class Log {

    /*
# Логи - это записи в консоли либо файле, которые позволяют узнать больше информации о происходящем во фреймворке во время
 выполнения тестов.
Что логировать? - Все по максимуму, чтобы понимать, что происходило в тесте не видя браузера
Что не логировать? - Избегать логов со статичным контентом, лучше всегда логировать какие-либо переменные
<!-- Source: https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.26.0</version>
    <scope>compile</scope>
</dependency>

<!-- Source: https://mvnrepository.com/artifact/tools.jackson.dataformat/jackson-dataformat-yaml -->
<dependency>
    <groupId>tools.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>3.0.2</version>
    <scope>compile</scope>
</dependency>

# Уровни логирования (от максимального к минимальному):
| Уровень | Когда использовать |
|---------|-------------------|
| **ERROR** | Ошибка, которая прервала тест |
| **WARN**  | Потенциальная проблема (re-run, скип) |
| **INFO**  | Основные шаги теста (открытие страницы, ввод данных) |
| **DEBUG** | Для отладки (локаторы, значения переменных) |
| **TRACE** | Максимально детально (почти никогда) |
В автотестах чаще всего используют **INFO**, **WARN**, **ERROR**.

## Важно: формат логов в Log4j2
❌ Неправильно (как в System.out.println):
log.info("message: %s %s", text, label);
✅ Правильно (Log4j2 использует {}):
log.info("message: {} {}", text, label);

# Использование.
Логирование работает по капотом ламбока. Классы, которые нужно логировать помечаются аннотацией @Log4j2
@Log4j2, а методы в пейджах помечаются аннотацией  log.info("Writing '{}' in to '{}'", text, label);
public class LogTest {
    @Test
    public void test() {
//        уровни логирования
        log.error("error");
        log.warn("warn");
        log.info("info"); // в автотестировании он в основном используется
        log.debug("debug");
        log.trace("trace");
        log.fatal("fatal");
    }
}

# Настройка логирования, настраивается для логов уровня INFO - файл log4j2-test.yaml,который лежит в src/test/resources
#https://springframework.guru/log4j-2-configuration-using-yaml/
#https://logging.apache.org/log4j/2.x/manual/configuration.html
Configuration:
  status: INFO
  Properties:
    Property:
      - name: "DefaultPattern"
        value: "%d{yyyy-MM-dd HH:mm:ss.SSS} %level [%t] [%c] - %msg%n"
  Appenders:
    Console:
      name: consoleAppender
      target: SYSTEM_OUT
      PatternLayout:
        Pattern: ${DefaultPattern}
    File:
      name: fileAppender
      fileName: "target/tests.log" // в эту папку складываются логи
      PatternLayout:
        Pattern: ${DefaultPattern}
  Loggers:
    logger:
      -
        name: consoleLogger
        level: INFO
        additivity: true
        AppenderRef:
          ref: consoleAppender
      -
        name: fileLogger
        level: INFO
        additivity: false
        AppenderRef:
          ref: fileAppender
    Root:
      level: INFO
      AppenderRef:
        - ref: consoleAppender
          level: INFO
        - ref: fileAppender
          level: INFO

или
<?xml version="1.0" encoding="UTF-8"?>
<Configuration xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xmlns="https://logging.apache.org/xml/ns"
               xsi:schemaLocation="
                   https://logging.apache.org/xml/ns
                   https://logging.apache.org/xml/ns/log4j-config-2.xsd">
    <Appenders>
        <Console name="CONSOLE">
            <PatternLayout pattern="%p - %m%n"/>
        </Console>
        <File name="MAIN" fileName="logs/main.log">
            <JsonTemplateLayout/>
        </File>
        <File name="DEBUG_LOG" fileName="target/info.log">
            <PatternLayout pattern="%d [%t] %p %c - %m%n"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="INFO">
            <AppenderRef ref="CONSOLE" level="INFO"/>
            <AppenderRef ref="MAIN"/>
        </Root>
        <Logger name="org.example" level="INFO">
            <AppenderRef ref="DEBUG_LOG"/>
        </Logger>
    </Loggers>
</Configuration>

После того как настроили логирование в проекте, нужно изменить рейтрай и тестлистнер - установить там место вывода на печать
 запись лога через log.warn, в тестлистнере заменить %s на {}
@Log4j2
public class Retry implements IRetryAnalyzer {
    private int attempt = 1;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                attempt++;
                iTestResult.setStatus(ITestResult.FAILURE);
                log.warn("Retrying once again");
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }
}


public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("======================================== STARTING TEST {} ========================================%n", iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("======================================== FINISHED TEST {} Duration: {} ========================================%n",
                iTestResult.getName(), getExecutionTime(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("======================================== FAILED TEST {} Duration: {} ========================================%n",
                iTestResult.getName(), getExecutionTime(iTestResult));
        // Скриншот при падении, объявляется драйвер, который будет находится в контексте прохождения теста
        WebDriver driver = (WebDriver) iTestResult.getTestContext().getAttribute("driver");
        if (driver != null) {
            AllureUtils.takeScreenshot(driver);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("======================================== SKIPPING TEST {} ========================================%n", iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // Не используется
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // Не используется
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // Не используется
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}

     */
}
