package io.igorcossta.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    USER(Set.of(
            Permission.USER_COMMENT_READ, Permission.USER_COMMENT_CREATE, Permission.USER_COMMENT_UPDATE, Permission.USER_COMMENT_DELETE,
            Permission.USER_THREAD_READ, Permission.USER_THREAD_CREATE, Permission.USER_THREAD_UPDATE, Permission.USER_THREAD_DELETE,
            Permission.USER_ACCOUNT_READ, Permission.USER_ACCOUNT_UPDATE
    )),
    OPERATOR(Set.of(
            Permission.OPERATOR_COMMENT_READ, Permission.OPERATOR_COMMENT_CREATE, Permission.OPERATOR_COMMENT_UPDATE, Permission.OPERATOR_COMMENT_DELETE,
            Permission.OPERATOR_THREAD_READ, Permission.OPERATOR_THREAD_CREATE, Permission.OPERATOR_THREAD_UPDATE, Permission.OPERATOR_THREAD_DELETE,
            Permission.OPERATOR_FORUM_READ, Permission.OPERATOR_FORUM_CREATE, Permission.OPERATOR_FORUM_UPDATE, Permission.OPERATOR_FORUM_DELETE,
            Permission.OPERATOR_COLLECTION_READ, Permission.OPERATOR_COLLECTION_CREATE, Permission.OPERATOR_COLLECTION_UPDATE, Permission.OPERATOR_COLLECTION_DELETE,
            Permission.OPERATOR_ACCOUNT_READ, Permission.OPERATOR_ACCOUNT_CREATE, Permission.OPERATOR_ACCOUNT_UPDATE, Permission.OPERATOR_ACCOUNT_DELETE,

            Permission.USER_COMMENT_READ, Permission.USER_COMMENT_CREATE, Permission.USER_COMMENT_UPDATE, Permission.USER_COMMENT_DELETE,
            Permission.USER_THREAD_READ, Permission.USER_THREAD_CREATE, Permission.USER_THREAD_UPDATE, Permission.USER_THREAD_DELETE,
            Permission.USER_ACCOUNT_READ, Permission.USER_ACCOUNT_UPDATE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = permissions
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
                .collect(Collectors.toList());
        return authorities;
    }
}
