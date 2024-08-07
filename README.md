# Weather Data Collector

## Описание

Weather Data Collector — это веб-приложение для сбора и управления данными о погоде. Приложение позволяет регистрировать сенсоры и измерения, а также просматривать статистику по дождливым дням.

## Технологии

- Java 17
- Spring Boot
- Hibernate
- ModelMapper
- Jakarta EE
- PostgreSQL (или любая другая база данных)

## Структура проекта

### Контроллеры

1. **`MeasurementsController`**

   Управляет запросами, связанными с измерениями погоды.

   - **GET `/measurements`**: Возвращает список всех измерений.
   - **GET `/measurements/rainyDaysCount`**: Возвращает количество дней с дождем.
   - **POST `/measurements/add`**: Добавляет новое измерение.

2. **`SensorsController`**

   Управляет запросами, связанными с сенсорами.

   - **POST `/sensor/registration`**: Регистрирует новый сенсор.

### DTO (Data Transfer Objects)
