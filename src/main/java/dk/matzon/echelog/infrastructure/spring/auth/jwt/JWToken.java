package dk.matzon.echelog.infrastructure.spring.auth.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class JWToken implements Authentication {

    private String rawToken;

    private User user;

    private boolean authenticated = false;

    public JWToken(User _user, boolean _authenticated) {
        user = _user;
        authenticated = _authenticated;
    }

    public JWToken(String _rawToken) {
        this(null, false);
        rawToken = _rawToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return (user == null) ? new ArrayList<GrantedAuthority>() : user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return (user == null) ? null : user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return (user == null) ? null : user.getUsername();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean _authenticated) throws IllegalArgumentException {
        if(user == null) {
            throw new IllegalArgumentException("Cannot authenticate null user");
        }
        authenticated = _authenticated;
    }

    @Override
    public String getName() {
        return (user == null) ? null : user.getUsername();
    }

    public String getRawToken() {
        return rawToken;
    }
}
