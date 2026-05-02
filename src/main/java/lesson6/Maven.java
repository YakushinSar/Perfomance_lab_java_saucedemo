package lesson6;

public class Maven {
    /*
# Maven - это инструмент для автоматизации сборки проектов на основе описания их структуры в файлах на языке POM (англ. Project Object Model),
 являющемся подмножеством XML. Аналоги - Gradle, Ant.
Maven нужен для:
- Зависимости (dependencies)
- Параметры (properties)
- Управление жизненным циклом проекта (LifeCycle)
- Запуск тестов из командной строки (CLI)

# Стандартная структура Maven-проекта
text
project/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/          ← бизнес-логика (Page Objects)
│   └── test/
│       └── java/          ← тесты
└── target/                ← сгенерировано Maven

# Всю бизнес-логику (все классы и данные которые мы используем в тестах) переносим в main из test, в test остаются только тесты.
Сравнение подходов: где хранить Page Objects
Критерий	                         Всё в src/test	               Page страниц в src/main
Простота	                          ✅ Проще	                            ❌ Сложнее
Переиспользование между проектами	  ❌ Нельзя	                            ✅ Можно
Зависимости	                          ❌ TestNG/JUnit везде	                ✅ Только в тестах

# pom.xml (Project Object Model) — это основной конфигурационный файл Maven-проекта, описывающий его структуру, зависимости,
 плагины, фазы сборки и многое другое.
# Структура pom.xml - все теги сначала открываются, а затем закрываются.
<project> -корневой тег файла pom.xml
<groupId> - идентификатор организации или компании
<artifactId> - название самого артефакта
<version> - версия текущего проекта
<packaging> - формат артефакта: jar, war, pom и т.д.
<dependencies> - список библиотек, необходимых проекту
<build> - конфигурация сборки и используемых плагинов для проекта
<plugins> - плагины, участвующие в процессе сборки
<properties> - свойства(настройки) переиспользуемые в разных частях pom.xml
<dependencyManagement> - централизованное управление версиями зависимостей в дочерних модулях

# Все библиотеки для Maven хранятся тут: https://mvnrepository.com/
# Обновление версий для Maven:
mvn versions:display-dependency-updates - команда для просмотра доступных обновлений библиотек
mvn versions:use-latest-versions - команда для автоматического обновления всех версий

# Внутри pom.xml можно использовать переменные для упрощения поддержки версионности/других целей, то есть номер версии
//меняется на запись вида ${testng.version}. Это делается чтобы в проперти было видно сразу все версии библиотек чтобы не скроллить вниз.
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <testng.version>7.10.2</testng.version>
</properties>

<!-- https://mvnrepository.com/artifact/org.testng/testng-->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>${testng.version}</version>
    <scope>test</scope>
</dependency>

# Scope в pom.xml- зона видимости библиотеки. Изначально, без указания тега <scope> или если указано <scope>compile</scope> - все зависимости доступны по всему
 проекту, т.е. вы можете сделать импорт библиотеки из класса, находящегося в main.java и в test.java. Если какая-то библиотека
 используется только в test.java - рекомендуется указать <scope>test</scope>.
<!-- https://mvnrepository.com/artifact/org.testng/testng -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>${testng.version}</version>
    <scope>test</scope>
</dependency>

# Папка Target - служебная директория, которую создает Maven при сборке проекта. Она содержит все артефакты и промежуточные
 файлы, полученные в результате выполнения различных фаз сборки проекта. Там в папке surefire-reports есть информация о
 всех тестах - это самая главная папка для тестировщика.

# Lifecycle - жизненный цикл Maven, основные фазы:
clean -> Удаляет папку target (очищает проект)
compile -> Компилирует исходный код (src/main/java)
test-compile -> Компилирует тесты (src/test/java)
test -> Запускает тесты
install -> Устанавливает проект в локальный репозиторий
Важно: каждая фаза автоматически выполняет все предыдущие:
clean  →  удалить target
         ↓
compile  →  скомпилировать main
         ↓
test-compile → скомпилировать тесты
         ↓
test    →  запустить тесты
         ↓
install →  установить в .m2

# Maven: выборочный запуск тестов. Синтаксис:
mvn -D<имя_свойства>=<значение> <фаза>
mvn	Вызов Maven
-Dtest=LoginTest	Устанавливаем системное свойство test со значением LoginTest
test	Фаза жизненного цикла (запустить тесты)

Один класс:          mvn -Dtest=LoginTest test
Один метод:          mvn -Dtest=LoginTest#testLogin test
Несколько методов:   mvn -Dtest=LoginTest#testLogin+testLogout test
Шаблон:              mvn -Dtest="*Test" test
Исключение:          mvn -Dtest="*Test, !BrokenTest" test
Пакет:               mvn -Dtest="lesson5JUnit5.tests.*" test

# Запуск testng.xml файла
-Вариант 1: Указывать путь каждый раз
mvn clean test -DsuiteXmlFile=src/test/resources/SmokeTest.xml
-Вариант 2: Настроить в pom.xml (чтобы не писать путь в ручную)
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.0.0-M4</version>
    <configuration>
        <suiteXmlFiles>
            <suiteXmlFile>${suiteXmlFile}</suiteXmlFile>
        </suiteXmlFiles>
    </configuration>
</plugin>

# Maven: передача параметров в тесты
Команда:           mvn test -D<имя_свойства>=<значение>
В Java:            System.getProperty("имя_свойства")
Пример:
mvn test -Dbrowser=chrome

@Test
public void test() {
    String browser = System.getProperty("browser", "chrome");
    System.out.println("Браузер: " + browser);
}
Важно: параметры доступны через System.getProperty()

     */
}
