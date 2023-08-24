package io.igorcossta.forum;

public class ForumNotFoundException extends RuntimeException {
    public ForumNotFoundException(String message) {
        super(message);
    }
}
