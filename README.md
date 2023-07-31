# BONUS

Сеервис управления бонусами
Демо приложение доступно по ссылке: http://79.137.197.181:8080/

## Переменные
Переменные окружения для настройки сервиса

| Имя          | Описание                 | Значение по-умолчанию                  |
|--------------|--------------------------|----------------------------------------|
| DB_URL       | url для подключения к БД | jdbc:postgresql://localhost:5432/bonus |
| DB_USERNAME  | Имя пользователя БД      | bonus                                  |
| DB_PASSWORD  | Пароль пользователя БД   | bonus                                  |

## Локальный запуск

### Запуск БД
    
``` shell
docker run --name bonus-db -e POSTGRES_USER=bonus -e POSTGRES_PASSWORD=bonus -e POSTGRES_DB=bonus -p 5432:5432 -d postgres:12-alpine
```

### Запуск проекта

``` shell
./gradlew -Dorg.gradle.java.home=/jdk11_path_directory bootRun
```
где jdk11_path_directory - путь до JDK 11 


## Локальный запуск docker-compose

``` shell
docker-compose up
```

### 