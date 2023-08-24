package io.igorcossta.thread;

public class DuplicateThreadException extends RuntimeException {
    public DuplicateThreadException(String message) {
        super(message);
    }
}
