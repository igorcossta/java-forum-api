package io.igorcossta.token;

import io.igorcossta.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RequiredArgsConstructor
public class TokenControllerAdvice {

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorMessage onActivationTokenNotFound(TokenNotFoundException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }

    @ExceptionHandler(TokenAlreadyActivatedException.class)
    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    public ErrorMessage onTokenAlreadyActivatedException(TokenAlreadyActivatedException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(GONE)
    @ResponseBody
    public ErrorMessage onTokenExpiredException(TokenExpiredException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }
}
