package io.igorcossta.token;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private boolean isActivate;

    @Column(nullable = false)
    private String tokenURL;

    public Token(UUID userId, String token, String tokenType, LocalDateTime expiresAt, boolean isActivate, String tokenURL) {
        this.userId = userId;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
        this.isActivate = isActivate;
        this.tokenURL = tokenURL;
    }
}
