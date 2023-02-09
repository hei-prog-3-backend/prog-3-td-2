package app.foot.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class NotAllowedException extends RuntimeException{

    public NotAllowedException(String message){
        super(message);
    }

}
