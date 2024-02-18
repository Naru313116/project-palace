package pl.rest.webservices.restfulwebservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserPostNotFoundException extends RuntimeException {
    public UserPostNotFoundException(String message) {
        super(message);
    }
}
