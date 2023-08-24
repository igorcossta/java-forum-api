package io.igorcossta.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    /*
     * user permissions
     * */

    USER_COMMENT_READ("user_comment:read"),
    USER_COMMENT_CREATE("user_comment:create"),
    USER_COMMENT_UPDATE("user_comment:update"),
    USER_COMMENT_DELETE("user_comment:delete"),

    USER_THREAD_READ("user_thread:read"),
    USER_THREAD_CREATE("user_thread:create"),
    USER_THREAD_UPDATE("user_thread:update"),
    USER_THREAD_DELETE("user_thread:delete"),

    USER_ACCOUNT_READ("user_account:read"),
    USER_ACCOUNT_UPDATE("user_account:update"),

    /*
     * operator permissions
     * */

    OPERATOR_COMMENT_READ("operator_comment:read"),
    OPERATOR_COMMENT_CREATE("operator_comment:create"),
    OPERATOR_COMMENT_UPDATE("operator_comment:update"),
    OPERATOR_COMMENT_DELETE("operator_comment:delete"),

    OPERATOR_THREAD_READ("operator_thread:read"),
    OPERATOR_THREAD_CREATE("operator_thread:create"),
    OPERATOR_THREAD_UPDATE("operator_thread:update"),
    OPERATOR_THREAD_DELETE("operator_thread:delete"),

    OPERATOR_FORUM_READ("operator_forum:read"),
    OPERATOR_FORUM_CREATE("operator_forum:create"),
    OPERATOR_FORUM_UPDATE("operator_forum:update"),
    OPERATOR_FORUM_DELETE("operator_forum:delete"),

    OPERATOR_COLLECTION_READ("operator_collection:read"),
    OPERATOR_COLLECTION_CREATE("operator_collection:create"),
    OPERATOR_COLLECTION_UPDATE("operator_collection:update"),
    OPERATOR_COLLECTION_DELETE("operator_collection:delete"),

    OPERATOR_ACCOUNT_READ("operator_account:read"),
    OPERATOR_ACCOUNT_CREATE("operator_account:create"),
    OPERATOR_ACCOUNT_UPDATE("operator_account:update"),
    OPERATOR_ACCOUNT_DELETE("operator_account:delete");

    @Getter
    private final String permission;
}
