package io.igorcossta.thread;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ThreadType {
    SOLVED("SOLVED"),
    CLOSED("CLOSED"),
    QUESTION("QUESTION"),
    GUIDE("GUIDE");

    @Getter
    private final String type;

}
