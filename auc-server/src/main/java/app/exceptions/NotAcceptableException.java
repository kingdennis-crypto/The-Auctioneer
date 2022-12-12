package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotAcceptableException extends RuntimeException {
    public NotAcceptableException() {
        super();
    }

    public NotAcceptableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAcceptableException(String message) {
        super(message);
    }

    public NotAcceptableException(Throwable cause) {
        super(cause);
    }
}
