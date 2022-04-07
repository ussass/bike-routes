# Bike routes / Велосипедные маршруты

## Функционал

Для администратора системы:
-  авторизация
-  просмотр пользователей
-  просмотр/добавление/изменение/удаление маршрутов

Для пользователей:
-  авторизация
-  просмотр маршрутов

## Запуск проекта
Для запуска проекта необходимо клонировать проет с репозитория и запустить в среде разработки.

в файле src\main\resourcesapplication.properties необходимо указать параметры базы данных
-  **```spring.datasource.url=```** - URL базы данных
-  **```spring.datasource.username=```** - имя пользователя для базы данных
-  **```spring.datasource.password=```** - пароль для базы данных

```Хост``` http://localhost:8080


# Документация к API

## Авторизация - `POST /api/v1/users/login`

**Авторизация:** не требуется

**Запрос:**

```json
{
  "login": "admin",
  "password": "admin"
}
```

* `login` - логин
* `password` - пароль


## Регистрация - `POST /api/v1/users/signup`

**Авторизация:** не требуется

**Запрос:**

```json
{
  "login": "newuser",
  "password": "password"
}
```

* `login` - логин
* `password` - пароль

## Список пользователей `GET /api/v1/users`

**Авторизация:** требуется

**Роль:** Admin

**Пример ответа:**

```json
[
    {
        "login": "admin",
        "role": "ADMIN"
    },
    {
        "login": "user",
        "role": "USER"
    }
]
```

* `login` - логин пользователя
* `role` - роль пользователя

## Список маршрутов `GET /api/v1/routes`

**Авторизация:** требуется

**Роль:** Admin, User

**Пример ответа:**

```json
[
    {
        "id": 1,
        "title": "circle movement"
    }
]
```

* `id` - номер маршрута
* `title` - название маршрута

## Просмотр маршрута `GET /api/v1/routes/{id}`

**Авторизация:** требуется

**Роль:** Admin, User

**Параметры:**

* `id` - id маршрута

**Пример ответа:**

```json
{
    "id": 1,
    "title": "circle movement",
    "description": "turn left at every turn"
}
```

* `id` - номер маршрута
* `title` - название маршрута
* `description` - описание маршрута

## Добавление маршрута `POST /api/v1/routes`

**Авторизация:** требуется

**Роль:** Admin

**Параметр запроса:**

```json
{
    "title": "title",
    "description": "description"
}
```

* `title` - название маршрута
* `description` - описание маршрута

## Изменение маршрута `PUT /api/v1/routes/{id}`

**Авторизация:** требуется

**Роль:** Admin

**Параметры:**

* `id` - id маршрута

**Параметр запроса:**

```json
{
    "title": "new title",
    "description": "new description"
}
```

* `title` - название маршрута
* `description` - описание маршрута

## Удаление опроса `DELETE /api/v1/routes/{id}`

**Авторизация:** требуется

**Роль:** Admin

**Параметры:**

* `id` - id маршрута
  