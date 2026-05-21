package lesson10;

public class Log {

    /*
# Логи - это записи в консоли либо файле, которые позволяют узнать больше информации о происходящем во фреймворке во время
 выполнения тестов.
Что логировать? - Все по максимуму, чтобы понимать, что происходило в тесте не видя браузера
Что не логировать? - Избегать логов со статичным контентом, лучше всегда логировать какие-либо переменные

# Зависимости
<!-- Source: https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.26.0</version>
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

# Настройка логирования - существует два способа формата конфигурации:log4j2-test.yaml/log4j2.yaml или log4j2.xml
# Способ 1: log4j2-test.yaml
1.Создать в src/test/resources файл log4j2-test.yaml
Ссылка https://springframework.guru/log4j-2-configuration-using-yaml/
Пример:
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

2. log4j2-test.yaml — для парсинга YAML файлов Log4j2 требуется дополнительная зависимость jackson-dataformat-yaml
<!-- Source: https://mvnrepository.com/artifact/tools.jackson.dataformat/jackson-dataformat-yaml -->
<dependency>
    <groupId>tools.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
    <version>3.0.2</version>
    <scope>compile</scope>
</dependency>

# Способ 2: Настройка логирования log4j2.xml
Важно: если в папке `src/test/resources` есть файлы `log4j2-test.yaml` или `log4j2.yaml`, Log4j2 использует их, а `log4j2.xml` игнорируется.
Куда класть файл:`src/test/resources/log4j2.xml`
### Основные элементы:
- **Console** — выводит логи в консоль
- **File** — сохраняет логи в файл
- **Logger** — настройка для вашего пакета (замените `your.project.package`)
- **Root** — настройка для всех остальных классов
### Параметры:
- `additivity="false"` — логи не дублируются в родительский логгер
- `level="INFO"` — уровень логирования
- `fileName` — путь к файлу с логами
### Как использовать шаблон:
1. Скопируйте файл `log4j2.xml` в `src/test/resources/`
2. Замените `your.project.package` на название вашего корневого пакета (например, `homework3`, `homework8`, `com.myproject`)
### Шаблон для копирования:

<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Appenders>
        <!-- Вывод в консоль -->
        <Console name="CONSOLE">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>

        <!-- Вывод в файл (папка target/logs/) -->
        <File name="FILE" fileName="target/logs/tests.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %c - %msg%n"/>
        </File>
    </Appenders>

    <Loggers>
        <!-- Логгер для вашего проекта -->
        <Logger name="your.project.package" level="INFO" additivity="false">
            <AppenderRef ref="CONSOLE"/>
            <AppenderRef ref="FILE"/>
        </Logger>

        <!-- Корневой логгер для всех остальных -->
        <Root level="INFO">
            <AppenderRef ref="CONSOLE"/>
        </Root>
    </Loggers>
</Configuration>

# Использование.
Логирование работает под капотом ламбока. Классы, которые нужно логировать помечаются аннотацией @Log4j2, а методы в пейджах,
 классах wrappers, TestListener помечаются аннотацией  log.info("Writing '{}' in to '{}'", text, label);
 Логирование в PageTest считают избыточным, потому что:
-Логи уже есть в PageObject'ах и Wrappers (они описывают каждое действие)
-TestListener уже логирует начало, успех, падение и время теста
-Если залогировать каждый шаг в тесте, логи станут слишком громоздкими
Тест (без логов)
    ↓ вызывает
PageObject (с логами) → логирует каждое действие
    ↓ вызывает
Wrappers (с логами) → логируют конкретные действия с элементами

# Важно: формат логов в Log4j2
❌ Неправильно (как в System.out.println):
log.info("message: %s %s", text, label);
✅ Правильно (Log4j2 использует {}):
log.info("message: {} {}", text, label);

# После того как настроили логирование в проекте, нужно изменить Retry и TestListener - установить там место вывода на печать
 запись лога через log.warn, в TestListener заменить %s на {}
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
