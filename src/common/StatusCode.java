package common;

import java.io.Serializable;

/**
 * Этот Enum является основой системы.
 * Не удалять и не изменять без согласования!
 */

public enum StatusCode implements Serializable {
    // Group 2xx: Success
    OK(200, "Операция выполнена успешно"),
    CREATED(201, "Объект успешно создан"),
    CONTINUE(202, "ID валиден. Пожалуйста, продолжите ввод данных"),

    // Group 4xx: Client errors
    BAD_REQUEST(400, "Некорректный запрос. Данные имеют неверную структуру"),
    ID_INVALID(401, "ID не соответствует условиям"),
    ID_TAKEN(402, "Данный ID уже занят"),
    NAME_INVALID(403, "Имя не соответствует условиям"),
    FORMAT_INVALID(404, "Неверный формат данных (ошибка типа данных)"),
    OUT_OF_RANGE(405, "Значение вне допустимого диапазона"),
    REQUIRED_FIELD_MISSING(406, "Отсутствуют обязательные поля"),

    // Group 5xx: Server or System errors
    SERVER_ERROR(500, "Внутренняя ошибка сервера. Попробуйте позже"),
    SERVICE_UNAVAILABLE(503, "Сервис временно недоступен"),
    TIMEOUT(504, "Время ожидания истекло. Соединение закрыто");

    private final int code;
    private final String description;

    StatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Утилита для быстрой проверки успешного выполнения
    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", code, description);
    }
}
