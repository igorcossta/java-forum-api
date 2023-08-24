package io.igorcossta.token;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TokenType {
    ACTIVATE("token:activate_account");

    private final String tokenType;

    public String get() {
        return tokenType;
    }
}
