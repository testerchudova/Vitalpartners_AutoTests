# Проект по автоматизации тестирования Vitalpartners

> Проект включает в себя UI-автотесты для формы обратной связи на странице цен с использованием современного стека технологий и глубокой интеграции в CI/CD процессы.


## 🛠 Технологический стек

<p align="center">
  <img title="IntelliJ IDEA" alt="IntelliJ IDEA" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/intellij/intellij-original.svg" width="45" height="45"/>
  <img title="Java" alt="Java" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="45" height="45"/>
  <img title="GitHub" alt="GitHub" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg" width="45" height="45"/>
  <img title="JUnit 5" alt="JUnit 5" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/junit/junit-original.svg" width="45" height="45"/>
  <img title="Gradle" alt="Gradle" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-original.svg" width="45" height="45"/>
  <img title="Jenkins" alt="Jenkins" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg" width="45" height="45"/>
  <img title="Telegram" alt="Telegram" src="https://cdn.simpleicons.org/telegram" width="45" height="45"/>
  <img title="Jira" alt="Jira" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg" width="45" height="45"/>
</p>


* **Язык**: Java 17
* **Фреймворки**: Selenide, JUnit 5
* **Сборка**: Gradle 8.x
* **Отчетность**: Allure Report, Allure TestOps
* **Инфраструктура**: Jenkins, Selenoid (Docker)
* **Уведомления**: Telegram Bot
* **Библиотеки**: JavaFaker (тестовые данные), AspectJ (агенты)

---

## 📝 Что реализовано
* [x] **Page Object Model** - логика взаимодействия с элементами вынесена в отдельные классы.
* [x] **Валидация HTML5** - проверка системных сообщений браузера и атрибутов полей.
* [x] **Параметризация** - запуск в разных браузерах и размерах окна через Jenkins.
* [x] **Интеграция с Jira** - привязка автотестов к тикетам через аннотации `@Issue`.
* [x] **Интеграция с Allure TestOps** - автоматическая выгрузка результатов и управление запусками.

---

## 🏗 Инфраструктура проекта

### 1. Jenkins CI/CD
Настроен Pipeline для сборки проекта и прогона тестов по тегам.
<p align="center">
  <img src="media/screenshots/jenkins_main.png" width="850">
</p>

### 2. Selenoid (Удаленный запуск браузеров)
Тесты запускаются в изолированных Docker-контейнерах. Selenoid позволяет наблюдать за выполнением теста в реальном времени.
<p align="center">
  <img src="media/screenshots/selenoid.png" width="850">
</p>

---

## 📊 Мониторинг и отчетность

### 1. Allure TestOps
Централизованная система управления тестированием. Здесь хранятся все тест-кейсы и история запусков.
<p align="center">
  <img src="media/screenshots/testops.png" width="850">
  <img src="media/screenshots/testops1.png" width="850">
  <img src="media/screenshots/testops2.png" width="850">
</p>

### 2. Интеграция с Jira
Результаты прогонов отображаются непосредственно в тикетах Jira, обеспечивая прозрачность для всей команды.
<p align="center">
  <img src="media/screenshots/jira_integration.png" width="850">
</p>

### 3. Allure Report
Подробные отчеты с шагами выполнения, скриншотами и исходным кодом страницы в случае падения.
<p align="center">
  <img src="media/screenshots/allure_report.png" width="850">
   <img src="media/screenshots/allure_report1.png" width="850">
</p>

---

## 🔔 Уведомления в Telegram
После каждой сборки бот присылает краткий отчет со статистикой прохождения тестов.
<p align="center">
  <img src="media/screenshots/telegram.png" width="350">
</p>

---

### 🎥 Видео выполнения тестов
Использование Selenoid позволяет не только наблюдать за тестами в реальном времени, но и автоматически записывать видео каждого прогона. Это значительно ускоряет анализ причин падения тестов.

<p align="center">
  <video src="media/video1.mp4" width="800" controls muted></video>
</p>
---

## 🚀 Запуск проекта
### Локальный запуск
```bash
gradle clean test