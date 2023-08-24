package io.igorcossta.forum;

import io.igorcossta.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@RequiredArgsConstructor
public class ForumControllerAdvice {

    @ExceptionHandler(ForumNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    public ErrorMessage onForumNotFoundException(ForumNotFoundException ex) {
        return new ErrorMessage("ERROR", ex.getMessage());
    }
}
