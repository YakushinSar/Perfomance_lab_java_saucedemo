package lesson7;

public class AllureReport {

    /*
# Репортинг - это чуть ли не единственное, что может увидеть заказчик и команда по результатам вашей работы как автоматизатора.
 Не имея репортинга в какой-то момент может возникнуть вопрос, а что вы вообще делаете? Allure это одна из систем репортинга.
 https://github.com/borodicht/AllureReportingGoogle

# Allure - подключение
Плагин allure-maven используется для интеграции Allure отчётов с Maven. Это позволяет автоматически генерировать красивые
 и подробные отчёты о тестировании после выполнения тестов. Плагин allure-maven:
1. Находит сгенерированные результаты Allure (в папке allure-results).
2. Преобразует их в HTML-отчёт (в папку allure-report).
3. Позволяет локально открыть отчёт с помощью встроенного сервера.

# Интеграция Allure в проект
1. Сначала для установки Allure нужно в pom файл поместить плагин
<plugin>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-maven</artifactId>
    <version>2.29.0</version>
</plugin>
Все установленные плагины можно смотреть в Maven в Plugins. Нужно раскрыть allure там и нажать allure:install - после этого
 в дереве проекта появится папка allure и его версия.

2. После установки надо добавить в pom файл зависимость для TestNG или Junit5. Нужно добавить
- В <properties> задаём allure.version
<!-- Указываем версию Allure через свойство -->
<properties>
    <allure.version>2.29.0</allure.version>
</properties>

- В <dependencyManagement> импортируем allure-bom
<!-- Подключаем Allure BOM для централизованного управления версиями -->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-bom</artifactId>
            <version>${allure.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

- В <dependencies> добавляем allure-testng (или allure-junit5)
<!-- Добавляем конкретную зависимость Allure (для TestNG / JUnit 5) -->
<dependencies>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-testng</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

3. Создаем конфигурацию AspectJ. Allure использует AspectJ для функциональности аннотаций @Step и @Attachment.
- В <properties> задаём aspectj.version
<!-- Задаём версию AspectJ через свойство (меняем в одном месте) -->
<properties>
    <aspectj.version>1.9.22.1</aspectj.version>
</properties>

- В плагине surefire подключаем AspectJ через -javaagent
<!-- Настраиваем плагин запуска тестов -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.1.2</version>
    <configuration>
        <argLine>
            -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
        </argLine>
    </configuration>
</plugin>

- Добавляем зависимость aspectjweaver
<dependency>
    <!-- Зависимость для работы AspectJ -->
    <!-- aspectj — чтобы Allure "видел" выполнение тестов и добавлял в отчёт шаги и вложения -->
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>${aspectj.version}</version>
</dependency>

4. После добавления всех зависимостей -перейти в Maven и сделать Lifecycle clean

5. Можно запустить тест, в проекте появится папка allure-result, в ней история прохождения тестов с метаинформацией. Эту папку
 надо перенести в test.resurses создать там файл allure.properties. В файл поместить:
allure.results.directory=target/allure-results
allure.link.issue.pattern=https://jira.com/issues/{}
allure.link.tms.pattern=https://qase.com/cases/{}
где
-allure.results.directory -директория куда будут собираться результаты выполнения тестов
-allure.link.issue.pattern - шаблон ссылки, ведущей к баг-трекеру для аннотации @Issue
-allure.link.tms.pattern - шаблон ссылки, ведущей к системе тест-кейс менеджмента для аннотации @TmsLink
После создания фйла allure.properties папку allure-result в дереве проекта удалить.
Перейти в Maven и сделать Lifecycle clean и запустить тесты, allure-result появится после этого в target.

# Allure - использование
Перейти в Maven Plagins и там есть allure report и allure serve. Есть два способа генерации отчета:
1. Способ 1. После нажатия на allure report в target появляется папка site в ней есть файл index.html, нужно на него нажать и выбрать
 браузер в котором отобразится отчет. То есть команда report на локальном ПК формирует отчет в site.
- Сначала запускаем тесты (они создают allure-results)
mvn clean test
- Генерируем HTML-отчёт из результатов
allure generate
- Открываем отчёт в браузере
allure open

2. Способ 2. Одной командой (генерирует + открывает временный отчёт сразу)
allure serve
После команды надо остановить процесс генерации отчета - нажать красный квадрат, чтобы убить процессы виртуальных серверов

# Вкладки отчета Allure:
1. Overview - общая сводка по прогону: количество тестов, процент прохождения, приоритеты, длительность
- Блок ALLURE REPORT. Включает в себя дату и время прохождения теста, общее количество прогнанных кейсов, а также диаграмму
 с указанием процента и количества успешных, упавших и сломавшихся в процессе выполнения тестов.
- Блок TREND. Показывает тренд прохождения тестов от сборки к сборке.
- Блок SUITES. Показывает распределение результатов тестов по тестовым наборам.
- Блок ENVIRONMENT. Показывает тестовое окружение, на котором запускались тесты. Данная информация попадает в отчет из специального файла, расположенного в проекте.
- Блок CATEGORIES. Показывает распределение неуспешно прошедших тестов по видам дефектов.
- Блок FEATURES BY STORIES. Показывает распределение тестов по функционалу, который они проверяют.
- Блок EXECUTORS. Показывает исполнителя текущей сборки. Если выполнение производилось на инструменте CI (например, на Jenkins),
 то будет предоставлена информация о джобе и номере сборки.
2. Categories - группирует упавшие тесты по типам ошибок (например, "Product defect", "Test defect")
3. Suites - иерархия тестов по структуре кода (пакеты → классы → методы)
4. Graphs - визуализация результатов: диаграммы по статусам, длительности, классам
5. Timeline - визуализирует временные рамки прохождения каждого теста. (особенно полезна при параллельном запуске)
6. Behaviors - группировка тестов по проверяемому функционалу (Epic, Feature, Story).
7. Packages - группировка тестов по Java-пакетам, в которых лежат тестовые классы.

# Разметка тестов: Allure-аннотации позволяют сделать отчёт информативным: шаги, описание, ссылки на задачи и вложения.
- @Description -> Подробное описание теста
- @Step -> Шаг теста (с поддержкой параметров)
- @Epic -> Крупная бизнес-функция (масштабный блок)
- @Feature -> Конкретная функция внутри Epic
- @Story -> Сценарий использования внутри Feature
- @Severity -> Важность теста (BLOCKER, CRITICAL, NORMAL, MINOR, TRIVIAL)
- @Link -> Общая ссылка (документация, сайт)
- @TmsLink -> Ссылка на тест-кейс в TMS (с шаблоном в allure.properties)
- @Issue -> Ссылка на задачу в баг-трекере (с шаблоном в allure.properties)
properties
allure.link.issue.pattern=https://jira.com/issues/{}
allure.link.tms.pattern=https://qase.com/cases/{}
- @Flaky -> Пометка, что тест нестабильный (часто падает)
- @Owner -> Автор теста
- @Attachment -> Вложение (скриншот, лог)

# Описать каждый метод на страницах проекта чтобы в отчете было написано для чего этот метод. Для этого все pages проекта
 помечаются аннотацией @Step. Чтобы аннотация была видна в java, нужно в pom файле изменить область видимости для io.qameta.allure
 на <scope>compile</scope>
    @Step("Вход в магазин с данными : логин '{user}', пароль '{password}'")
    public void login(String user, String password) {
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

# Добавление скриншотов. В test в utils необходимо создать класс AllureUtils с методом создания скриншотов.
 @Attachment означает что мы присоединяем скриншот к нашим действиям в проекте. В основном скриншоты надо делать только в
 случае падения теста, другие скриншоты не нужно.
public class AllureUtils {
    @Attachment(value = "screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
Использование AllureUtils в тестовых методах:
    public void checkLoginWithPositiveCred() {
        getLoginPage().open();
        AllureUtils.takeScreenshot(DriverManager.getDriver());
        getLoginPage().isPageOpened();
        getLoginPage().login("standard_user", "secret_sauce");

        assertEquals(getProductsPage().getTitle(), "Products");
    }

Чтобы скриншот делался при падении автоматически, можно добавить в TestListener в пункт отвечающий за падение
@Override
public void onTestFailure(ITestResult result) {
    System.out.printf("======================================== FAILED TEST %s Duration: %ss ========================================%n",
            result.getName(), getExecutionTime(result));
    // Скриншот при падении
    AllureUtils.takeScreenshot(DriverManager.getDriver());
}
В BaseTest надо добавить аннотацию @Listeners({AllureTestNg.class,TestListener.class}) чтобы условие прописанное в TestListener
 применялось ко всем тестам.

     */
}
