package com.ecomifyapi.auth_service.infra.authenticate;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.domain.entities.User;
import com.ecomifyapi.auth_service.infra.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticateUserImpl implements AuthenticateUser {

    private final UserGateway userGateway;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public AuthenticateUserImpl(
            UserGateway userGateway,
            BCryptPasswordEncoder passwordEncoder,
            TokenService tokenService
    ) {
        this.userGateway = userGateway;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String authenticate(String email, String password) {
        User user = userGateway.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return tokenService.generateToken(user);
    }
}
