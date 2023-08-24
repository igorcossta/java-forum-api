package io.igorcossta.user;

import io.igorcossta.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class UserControllerAdvice {

    @ExceptionHandler(UserAccountNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorMessage onUserAccountNotFoundException(UserAccountNotFoundException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }

    @ExceptionHandler(DuplicateAccountException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorMessage onDuplicateAccountException(DuplicateAccountException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }
}
