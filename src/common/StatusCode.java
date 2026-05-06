package common;

import java.io.Serializable;

/**
 * Cái Enum này là linh hồn của hệ thống, đừng có xóa bừa!
 */
public enum StatusCode implements Serializable {
    // Group 2xx: Success
    OK(200, "Thao tác thành công"),
    CREATED(201, "Đã tạo đối tượng thành công"),
    CONTINUE(202, "ID hợp lệ, tiếp tục nhập dữ liệu đi cu"), // Chính là cái mày cần

    // Group 4xx: Client err
    BAD_REQUEST(400, "Dữ liệu gửi lên như rác"),
    ID_INVALID(401, "ID không thỏa mãn điều kiện add_if_max"),
    ID_TAKEN(402, "ID ngon nhưng có thằng nhanh tay húp mất rồi"),
    NAME_INVALID(403, "Name không thỏa mãn điều kiện add_if_max"),
    FORMAT_INVALID (404, "FORMAT_INVALID: User nhập chữ vào ô số, nhập 'abc' vào ô boolean"),
    OUT_OF_RANGE (405, "OUT_OF_RANGE: số int, long bị quá to hoặc quá nhỏ"),
    REQUIRED_FIELD_MISSING (406, "REQUIRED_FIELD_MISSING: chỗ bắt buộc phải chọn true/false mà nó bỏ trống"),

    // Group 5xx: Server or System err
    SERVER_ERROR(500, "Server đang ngáo, thử lại sau"),
    SERVICE_UNAVAILABLE(503, "Server is unavailable at this time"),
    TIMEOUT(504, "Chờ mày nhập liệu lâu quá, tao đóng kết nối đây");

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

    // quick check success util
    public boolean isSuccess() {
        return this.code >= 200 && this.code < 300;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", code, description);
    }
}
