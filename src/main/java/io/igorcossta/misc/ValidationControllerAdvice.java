package io.igorcossta.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@RequiredArgsConstructor
public class ValidationControllerAdvice {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleValidationException(BindException ex) {
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.add(new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
        );

        return new ErrorResponse("ERROR", fieldErrors);
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    class ErrorResponse {
        private String type;
        private List<FieldErrorDTO> errors;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    class FieldErrorDTO {
        private String field;
        private String message;
    }
}
