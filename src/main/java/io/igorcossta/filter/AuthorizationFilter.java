package io.igorcossta.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.igorcossta.jwt.JwtService;
import io.igorcossta.user.User;
import io.igorcossta.util.ErrorMessage;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String jwt, username;
        if (header == null || !header.startsWith("Bearer ")) {
            log.debug("jwt not found");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwt = header.substring(7);
            username = jwtService.extractUsername(jwt);
            log.debug("jwt found");

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = (User) userService.loadUserByUsername(username);
                log.debug("user found -> " + user.toString());

                if (jwtService.isTokenValid(jwt, user)) {
                    log.debug("token is valid");
                    var authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JwtException ex) {
            log.debug("JWT thrown an error -> " + ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            var errorMessage = new ErrorMessage("ERROR", ex.getMessage());
            response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
        }
    }

}
