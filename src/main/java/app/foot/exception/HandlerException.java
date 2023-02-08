package app.foot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(
            Exception ex, WebRequest request){
        return new ResponseEntity<Object>(
                "Bad request", new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler({ InternalServerException.class})
    public ResponseEntity<Object> handleInternalServerErrorException(
            Exception ex, WebRequest request){
        return new ResponseEntity<Object>(
                "Internal server error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler({ NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(
            Exception ex, WebRequest request){
        return new ResponseEntity<Object>(
                "Not found", new HttpHeaders(), HttpStatus.NOT_FOUND
        );
    }
}
