package io.igorcossta.token;

import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import io.igorcossta.util.SuccessMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static io.igorcossta.token.TokenType.ACTIVATE;


@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserService userService;
    @Value("${spring.mail.verify-host}")
    private String verify_mail;

    @Transactional
    public Token createToken(User user) {
        return save(new Token(user.getId(),
                UUID.randomUUID().toString(),
                ACTIVATE.get(),
                LocalDateTime.now().plusHours(24),
                false,
                verify_mail));
    }

    @Transactional
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Transactional
    public SuccessMessage activateToken(String token) {
        Token tken = tokenRepository.findTokenByToken(token)
                .orElseThrow(() -> new TokenNotFoundException("token %s not found".formatted(token)));

        if (tken.isActivate()) {
            throw new TokenAlreadyActivatedException("token %s is already activated".formatted(token));
        }

        if (tken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("token %s expired".formatted(token));
        }

        tken.setActivate(true);
        save(tken);

        User user = userService.activateUserAccount(tken.getUserId());
        return new SuccessMessage("SUCCESS", "your account %s was successfully activate".formatted(user.getUsername()));
    }
}
