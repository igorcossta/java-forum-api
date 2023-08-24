package io.igorcossta.registration;

import io.igorcossta.email.EmailService;
import io.igorcossta.mapper.UserMapper;
import io.igorcossta.token.Token;
import io.igorcossta.token.TokenService;
import io.igorcossta.user.DuplicateAccountException;
import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import io.igorcossta.util.SuccessMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserService userService;
    private final EmailService emailService;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    @Transactional
    public SuccessMessage register(RegistrationRequest request) {
        boolean isPresent = userService.exists(request.username());
        if (isPresent) {
            throw new DuplicateAccountException("email %s already taken".formatted(request.username()));
        }
        User saved = userService.save(userMapper.fromNewRegistration(request));

        Token token = tokenService.createToken(saved);

        emailService.sendSimpleMail(saved.getUsername(), saved.getFirstName(), saved.getLastName(), token);

        return new SuccessMessage("SUCCESS", "user registered successfully");
    }

}
