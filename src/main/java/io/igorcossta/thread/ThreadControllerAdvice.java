package io.igorcossta.thread;

import io.igorcossta.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RequiredArgsConstructor
public class ThreadControllerAdvice {

    @ExceptionHandler(ThreadNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorMessage onThreadNotFoundException(ThreadNotFoundException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }

    @ExceptionHandler(DuplicateThreadException.class)
    @ResponseStatus(CONFLICT)
    @ResponseBody
    public ErrorMessage onDuplicateThreadException(DuplicateThreadException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }

    @ExceptionHandler(ThreadNotOwnerException.class)
    @ResponseStatus(FORBIDDEN)
    @ResponseBody
    public ErrorMessage onThreadNotOwnerException(ThreadNotOwnerException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }
}
