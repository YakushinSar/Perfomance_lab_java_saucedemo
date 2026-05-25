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
name: Saucedemo build //назавние пайплайна

on: // тригеры по которым происходит сборка билда
      push:
        branches: [ "master" ]
      pull_request:
        branches: [ "master" ]
      workflow_dispatch:  # 👈 Добавь эту строчку, чтобы запускать вручную из интерфейса GitHub

jobs:
  build:

    runs-on: ubuntu-latest // на какой системе на удаленном сервере запусткается джоба

    steps: // шаги
    - uses: actions/checkout@v4 //  скачивает наш код с гитхаба на вирт машину runs-on: ubuntu-latest
    - name: Set up JDK 17 // уствноыка java 17, должна соответсствовать версии в pom файле
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    - name: Tests Run
       # Команда для запуска конкретного теста из твоего класса LoginTest
            run: mvn clean test -Dtest=LoginTest#checkLoginWithPositiveCred

Дополнительная инфа для уствновки в файл:
1. Установка Java
https://github.com/actions/setup-java
2. Установка браузеров
https://github.com/marketplace/actions/setup-br
owser

Нажать на кнопку commit changes после этоговгитхабе в разделе Actioms появится моя сборка. В Интеледжи идеии сделать pull
 чтобы подтянуть yml файл из гитхаб экшена.
## Важные моменты:

1. **Headless режим обязателен** — в CI окружении нет браузера с интерфейсом. Добавьте в ChromeOptions: `options.addArguments("--headless");`

2. **Сохранение артефактов** — после прогона тестов полезно загрузить логи и Allure-результаты:
   ```yaml
   - name: Upload test logs
     if: always()
     uses: actions/upload-artifact@v4
     with:
       name: test-logs
       path: target/logs

Запуск по расписанию (Cron)
Чтобы твои тесты, например, с группой smoke, запускались каждое утро в 9:00, добавь в файл .github/workflows/maven.yml блок schedule:

on:
  schedule:
    # ┌───────────── минута (0 - 59)
    # │ ┌───────────── час (0 - 23)
    # │ │ ┌───────────── день месяца (1 - 31)
    # │ │ │ ┌───────────── месяц (1 - 12)
    # │ │ │ │ ┌───────────── день недели (0 - 6, где 0 - воскресенье)
    # │ │ │ │ │
    # * * * * *
    - cron: '0 9 * * *'  # Каждый день в 9:00 UTC
Как это читать: 0 9 * * * — это "когда минуты = 0, часы = 9, каждый день, каждый месяц, любой день недели". Для проверки
 синтаксиса можно использовать сайт crontab.guru.

# Способы отключить GitHub Actions
1. Отключить конкретный workflow (самый простой)
GitHub → вкладка Actions → выбрать workflow "Saucedemo build" → кнопка "..." справа → Disable workflow
Включить обратно там же → Enable workflow.
2. Удалить yml файл
Просто удалить .github/workflows/maven.yml из репозитория — Actions перестанет запускаться.
3. Отключить все Actions для репозитория
GitHub → Settings → Actions → General → выбрать Disable actions → Save.

# Github Actions + Allure
Документация: https://allurereport.org/docs/integrations-github/
Пример: https://gist.github.com/borodicht/3012244324d079ca42429daa56b2b8c4
     */
}
