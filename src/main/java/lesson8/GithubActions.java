package lesson8;

public class GithubActions {

    /*
# Continuous Integration
Непрерывная интеграция (CI) — практика разработки программного обеспечения, которая заключается в постоянном слиянии рабочих
 копий в общую основную ветвь разработки (до нескольких раз в день) и выполнении частых автоматизированных сборок проекта для
 скорейшего выявления потенциальных дефектов и решения интеграционных проблем. Пример:
1. Сборка продукта
2. Запуск юнит-тестов
3. Запуск integration/UI тестов
4. Генерация репорта
5. Отсылка результатов в мессенджере/имейле

Способы запуска
1. Вручную
2. Запуск каждые N дней/часов/минут
3. Запуск после каждого коммита/PR

Основные инструменты CI
● https://jenkins.io - установка локально либо на сервер, бесплатное использование, но придется искать хостинг с доступом к файловой
системе
● https://docs.github.com/en/actions - облако. Интеграция с GitHub
● https://travis-ci.org - работает в облаке, бесплатный для продуктов с открытым кодом
● https://circleci.com - работает в облаке, бесплатный 2500 минут в неделю
● https://www.jetbrains.com/teamcity/ - работает в облаке, платный

В GitHub в проекте нужно перейти на вкладку Actions и выбрать Java with Maven, нажать конпку Конфигурация
 В файле удаляем лишние строки, указываем способ запуска тестов

Итоговый файл
   # This workflow will build a Java project with Maven, and cache/restore any dependencies to improve the workflow execution time
# For more information see: https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven

# This workflow uses actions that are not certified by GitHub.
# They are provided by a third-party and are governed by
# separate terms of service, privacy policy, and support
# documentation.

name: Saucedemo build

on:
  push:
    branches: [ "master" ]
  pull_request:
    branches: [ "master" ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    - name: Tests Run
      run: mvn clean test

    # Optional: Uploads the full dependency graph to GitHub to improve the quality of Dependabot alerts this repository can receive
    - name: Update dependency graph
      uses: advanced-security/maven-dependency-submission-action@571e99aab1055c2e71a1e2309b9691de18d6b7d6

Нажать на кнопку commit changes после этоговгитхабе в разделе Actioms появится моя сборка. В Интеледжи идеии сделать pull
 чтобы подтянуть yml файл из гитхаб экшена. В Бейсьесь нужно добавить options.addArguments("--headless");
     */
}
