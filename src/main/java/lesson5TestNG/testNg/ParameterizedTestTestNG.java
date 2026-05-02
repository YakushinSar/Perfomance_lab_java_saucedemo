package lesson5TestNG.testNg;

public class ParameterizedTestTestNG {

    /*
# TestNG. Параметризованные тесты. Используется тогда - когда тело теста одинаковое, но много наборов данных
1. @DataProvider - метод-источник данных, возвращает двумерный массив (строки = наборы данных, столбцы = параметры)
-name - опциональный параметр для метода, чтобы указать читабельное имя
-indices = {0,2} - Какие строки из Object[][] использовать (индексы строк, которые будут использоваться как параметры)
-parallel - Запускать ли тесты с этим провайдером в параллельных потоках (true / false)
2. @Test(dataProvider = "...") - связывает тест с источником данных. Параметры метода можно использовать в тесте

Важные правила
- Количество элементов в Object[] = количеству параметров в @Test
- Порядок элементов = порядку параметров.
// Строка данных
{"standard_user", "", "Epic sadface: Password is required"}
//    user ↑         password ↑        errorMessage ↑
// Вызов метода
negativeLogin(user, password, errorMessage);
- Типы элементов должны соответствовать типам параметров
- Можно передавать null. Но осторожно: вызов метода на null → NullPointerException
- Используйте ThreadLocal для не потокобезопасных объектов. При параллельном запуске (parallel = true) нужно, чтобы каждый поток имел свою копию (WebDriver, SoftAssert и т.д.)

# Набор параметров для параметризированных тестов
@DataProvider(name = "Тестовые данные для негативного логина", indices = {0, 1}, parallel = true)
public Object[][] loginData() {
    return new Object[][]{
        {"user1", "pass1", "error1"},
        {"user2", "pass2", "error2"},
        {"user3", "pass3", "error3"}
    };
}

# При запуске теста они будут браться параметры из тестового набора "Тестовые данные для негативного логина". Параметры
 выбираются строго по порядку следования в массиве Object[]
@Test(dataProvider = "Тестовые данные для негативного логина")
public void testLogin(String user, String pass, String error) {
    // тест будет запущен параллельно для разных наборов данных
    getLoginPage().open();
    getLoginPage().login(user, pass);
    assertEquals(getLoginPage().getErrorMessage(), error);
}
     */
}
