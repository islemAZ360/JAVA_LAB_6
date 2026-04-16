package common;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private boolean success;
    private Object data;


    public Response(String message, boolean success, Object data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getData() {
        return data;
    }
}