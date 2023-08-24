package io.igorcossta.email;

import io.igorcossta.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class EmailControllerAdvice {

    @ExceptionHandler(EmailFailedToSendException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage onEmailFailedToSendException(EmailFailedToSendException ex) {
        return new ErrorMessage("ERROR", "error sending the email verification.");
    }
}
