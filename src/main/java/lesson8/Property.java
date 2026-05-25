package lesson8;

public class Property {

    /*
Пример: как использовать секреты в коде (на основе твоего LoginTest)
1.В GitHub: Settings → Secrets and variables → Actions → New repository secret
Name: SAUCE_USER
Secret: standard_user

2.В файле .github/workflows/maven.yml: Передай секрет в переменную окружения.
- name: Tests Run
  env:
    USERNAME: ${{ secrets.SAUCE_USER }}
  run: mvn clean test -Duser=$USERNAME

3.В Java-коде (в твоём BaseTest.java): Прочитай переменную окружения или системное свойство.
protected String user = System.getProperty("user", System.getenv().getOrDefault("USERNAME", "default_user"));
     */
}
