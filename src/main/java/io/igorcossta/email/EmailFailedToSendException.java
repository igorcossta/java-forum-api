package io.igorcossta.email;

public class EmailFailedToSendException extends RuntimeException {
    public EmailFailedToSendException(String message) {
        super(message);
    }
}
