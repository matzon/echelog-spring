package dk.matzon.echelog.infrastructure.spring.auth;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenAuthenticationService {
    String addAuthentication(HttpServletResponse response, Authentication authentication);

    Authentication getAuthentication(HttpServletRequest request);

    Authentication authenticate(Authentication _authentication);
}
