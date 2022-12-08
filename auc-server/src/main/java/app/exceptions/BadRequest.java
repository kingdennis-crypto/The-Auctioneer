package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRequest extends RuntimeException {
    public BadRequest() {
        super();
    }

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequest(String message) {
        super(message);
    }

    public BadRequest(Throwable cause) {
        super(cause);
    }
}
