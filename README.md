# Тестовое задание

## Установка и настройка

### Шаг 1: Клонировать репозиторий

```bash
git clone https://github.com/ChzhenAlena/subscriptions.git
cd subscriptions
```

### Шаг 2: Создать файл .env

Создайте файл .env в корне проекта (ниже пример заполнения):

```ini
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_strong_password_here  # Рекомендуется использовать сложный пароль
POSTGRES_DB=test_task_db
```
### Шаг 3: Сборка и запуск приложения с Docker Compose
Собрать и запустить сервисы с помощью Docker Compose:

```bash
docker-compose up --build
```
Дополнительные команды:

Запуск в фоновом режиме: docker-compose up -d --build

Остановка: docker-compose down

Остановка с удалением томов: docker-compose down -v

### Шаг 4: Доступ к приложению
После запуска приложение будет доступно:

Swagger UI: http://localhost:8080/swagger-ui/index.html

Основное API: http://localhost:8080/api/

После запуска контейнера логи будут выводиться в консоль и в папку /logs в корне проекта


## Требования

- **Docker версии 20.10.0+**
- **Docker Compose версии 2.0.0+**
- **Для локальной разработки (опционально):** JDK 17, PostgreSQL 15

