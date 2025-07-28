# Todolist API (Java Spring)

## Описание проекта
Todolist API — это RESTful сервис для управления задачами (To-Do List), разработанный на Java Spring. В проекте реализованы:
- CRUD-операции для задач
- Интеграция со Swagger для удобной документации API
- Тестирование с использованием JUnit
- Глобальный перехватчик исключений
- Кастомная оболочка для исключений

## Технологии
- **Java 17**
- **Spring Boot 3**
- **Spring Web** (REST API)
- **Spring Data JPA** (работа с базой данных)
- **H2/PostgreSQL** (выбор базы данных)
- **Swagger (Springdoc OpenAPI)** (автодокументирование API)
- **JUnit 5 + Mockito** (тестирование)
- **Lombok** (упрощение кода)

## Установка и запуск

### 1. Клонирование репозитория
```sh
 git clone https://github.com/yourusername/todolist.git
 cd todolist
```

### 2. Настройка окружения
- Убедитесь, что установлен **Java 17+** и **Maven**.
- В файле `application.yml` настройте базу данных (например, PostgreSQL или H2 для тестов).

Пример `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:h2:mem:todolist
    driver-class-name: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
  h2:
    console:
      enabled: true
```

### 3. Сборка и запуск
Соберите и запустите проект:
```sh
mvn clean install
mvn spring-boot:run
```

После запуска API будет доступно по адресу: `http://localhost:8080`

### 4. Swagger UI
Документация API доступна по адресу:
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Тестирование
Запуск всех тестов:
```sh
mvn test
```

## Глобальный перехватчик исключений
В проекте реализован **глобальный перехватчик исключений** с аннотацией `@ControllerAdvice`. Он обрабатывает все исключения и возвращает структурированные ответы.

Пример обработки исключений:
```java
@ExceptionHandler(CustomNotFoundException.class)
public ResponseEntity<ApiError> handleNotFoundException(CustomNotFoundException ex) {
    ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
```

## Кастомные исключения
Создана собственная оболочка для исключений (`CustomException`), позволяющая передавать код ошибки и сообщение.

Пример:
```java
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message) {
        super(message);
    }
}
```

