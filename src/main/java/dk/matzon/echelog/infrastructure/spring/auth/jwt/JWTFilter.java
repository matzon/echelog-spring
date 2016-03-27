package dk.matzon.echelog.infrastructure.spring.auth.jwt;

import dk.matzon.echelog.infrastructure.spring.auth.TokenAuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;
    private TokenAuthenticationService tokenAuthenticationService;

    public JWTFilter(AuthenticationManager _authenticationManager, TokenAuthenticationService _tokenAuthenticationService) {
        authenticationManager = _authenticationManager;
        tokenAuthenticationService = _tokenAuthenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest _httpServletRequest, HttpServletResponse _httpServletResponse, FilterChain _filterChain) throws ServletException, IOException {
        try {
            Authentication initialAuthentication = tokenAuthenticationService.getAuthentication(_httpServletRequest);
            Authentication authenticated = authenticationManager.authenticate(initialAuthentication);
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        } catch (AuthenticationException e) {
            /* ignored */
        }
        _filterChain.doFilter(_httpServletRequest, _httpServletResponse);

    }

    @Override
    public void destroy() {

    }
}
