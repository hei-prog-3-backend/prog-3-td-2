package app.foot.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    public ApiException(int code, String message) {
        super(HttpStatus.valueOf(code) + " : " + message);
    }
}
