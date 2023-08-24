package io.igorcossta.authentication;

import io.igorcossta.jwt.JwtResponse;
import io.igorcossta.jwt.JwtService;
import io.igorcossta.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public JwtResponse authenticate(AuthenticationRequest request) {
        // if no user is found the authenticationEntryPoint will show an error
        Authentication authenticate = authManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        User user = (User) authenticate.getPrincipal();
        log.debug(user.toString());

        String token = jwtService.generateToken(user);
        return new JwtResponse(token);
    }
}
