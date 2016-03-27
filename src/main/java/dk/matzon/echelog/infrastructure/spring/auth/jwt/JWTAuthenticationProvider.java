package dk.matzon.echelog.infrastructure.spring.auth.jwt;

import dk.matzon.echelog.infrastructure.spring.auth.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private TokenAuthenticationService tokenAuthenticationService;

    public JWTAuthenticationProvider(TokenAuthenticationService _tokenAuthenticationService) {
        tokenAuthenticationService = _tokenAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication _authentication) throws AuthenticationException {
        return tokenAuthenticationService.authenticate(_authentication);
    }

    @Override
    public boolean supports(Class<?> _class) {
        return _class.isAssignableFrom(JWToken.class);
    }
}
