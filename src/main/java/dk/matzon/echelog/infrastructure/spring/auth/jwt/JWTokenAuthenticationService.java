package dk.matzon.echelog.infrastructure.spring.auth.jwt;

import dk.matzon.echelog.infrastructure.spring.auth.TokenAuthenticationService;
import dk.matzon.echelog.infrastructure.spring.auth.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class JWTokenAuthenticationService implements TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private TokenHandler tokenHandler;

    @Autowired
    public JWTokenAuthenticationService(TokenHandler _tokenHandler) {
        tokenHandler = _tokenHandler;
    }

    @Override
    public String addAuthentication(HttpServletResponse response, Authentication _authentication) {
        final User user = (User) _authentication.getDetails();
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        return new JWToken(token);
    }

    @Override
    public Authentication authenticate(Authentication _authentication) {
        User user = tokenHandler.parseUserFromToken(((JWToken) _authentication).getRawToken());
        if(user != null) {
            return new JWToken(user, true);
        }
        throw new BadCredentialsException("Invalid JWT token. Expired?");
    }
}