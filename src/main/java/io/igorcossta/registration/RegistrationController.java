package io.igorcossta.registration;

import io.igorcossta.token.TokenService;
import io.igorcossta.util.SuccessMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final TokenService tokenService;

    @PostMapping
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("permitAll()")
    public SuccessMessage registration(@RequestBody @Valid RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping("/token/activate")
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("permitAll()")
    public SuccessMessage activateRegistrationToken(@RequestParam("token") String token) {
        return tokenService.activateToken(token);
    }
}
