package common;

import java.io.Serializable;

/**
 * Этот Enum — душа системы, не удаляйте его просто так!
 */
public enum StatusCode implements Serializable {
    // Группа 2xx: Успешное выполнение
    OK(200, "Operation successful"),
    CREATED(201, "Object created successfully"),
    CONTINUE(202, "Valid ID, continue entering data"), // Это именно то, что нужно

    // Группа 4xx: Ошибки клиента
    BAD_REQUEST(400, "Invalid data provided"),
    ID_INVALID(401, "ID does not satisfy the condition"),
    ID_TAKEN(402, "ID is valid but already taken"),
    NAME_INVALID(403, "Name does not satisfy the condition"),
    FORMAT_INVALID(404, "FORMAT_INVALID: User entered text in a numeric field, or invalid boolean value"),
    OUT_OF_RANGE(405, "OUT_OF_RANGE: Integer or long value is too large or too small"),
    REQUIRED_FIELD_MISSING(406, "REQUIRED_FIELD_MISSING: Mandatory boolean field was left empty"),

    // Группа 5xx: Ошибки сервера или системы
    SERVER_ERROR(500, "Server is experiencing issues, try again later"),
    SERVICE_UNAVAILABLE(503, "Server is unavailable at this time"),
    TIMEOUT(504, "Connection timed out waiting for input");

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