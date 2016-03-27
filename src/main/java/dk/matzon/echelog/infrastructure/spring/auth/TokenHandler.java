package dk.matzon.echelog.infrastructure.spring.auth;

import org.springframework.security.core.userdetails.User;

public interface TokenHandler {
    User parseUserFromToken(String _token);

    String createTokenForUser(User _user);
}
