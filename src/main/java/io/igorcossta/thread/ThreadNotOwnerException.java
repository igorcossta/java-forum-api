package io.igorcossta.thread;

public class ThreadNotOwnerException extends RuntimeException {
    public ThreadNotOwnerException(String message) {
        super(message);
    }
}
