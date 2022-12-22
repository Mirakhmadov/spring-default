package example.server.payload.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CustomResponse {
    private String message;
    private boolean success;
    private Integer statusCode;
    private Timestamp timestamp;
    private Object object;


    public CustomResponse(String message, boolean success, Integer statusCode, Timestamp timestamp) {
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
        this.timestamp = timestamp;
    }
}
