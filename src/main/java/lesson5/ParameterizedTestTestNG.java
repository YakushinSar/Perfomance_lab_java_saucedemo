package lesson5;

public class ParameterizedTestTestNG {
    /*
TestNG. Параметризованные тесты. Используется тогда - когда тело теста одинаковое, но много наборов данных
1. @DataProvider - метод, который возвращает двумерный массив объектов
2. dataProvider - параметр, указывающий на метод с данными (может быть name или метод)
3. name - опциональный параметр для метода, чтобы указать читабельное имя
4. indices = {0,2} - Какие строки из Object[][] использовать (индексы строк, которые будут использоваться как параметры)
5. parallel	Запускать ли тесты с этим провайдером в параллельных потоках (true / false)
6. Параметры метода можно использовать в тесте

Важные правила
- Количество элементов в Object[] должно точно соответствовать количеству параметров в @Test.
- Типы должны строго соответствовать сигнатуре метода.
- Можно передавать null, но нужно быть осторожным (возможен NullPointerException).
- Используйте ThreadLocal, если вы передаёте объекты, не потокобезопасные (WebDriver, SoftAssert и т.д.).

    // Набор параметров для параметизированных тестов
    @DataProvider(name = "Тестовые данные для негативного логина", indices = {0,2})
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина")
    // Параметризованные тест, так как у теста есть параметры. При запуске теста они будут браться из тестового набора loginData
    public void negativeLogin(String user, String password, String errorMessage) {
        getLoginPage().open();
        getLoginPage().login(user, password);
        assertEquals(getLoginPage().getErrorMessage(), errorMessage);
    }

     */
}
