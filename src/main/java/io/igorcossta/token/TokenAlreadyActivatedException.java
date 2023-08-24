package io.igorcossta.token;

public class TokenAlreadyActivatedException extends RuntimeException {
    public TokenAlreadyActivatedException(String message) {
        super(message);
    }
}
