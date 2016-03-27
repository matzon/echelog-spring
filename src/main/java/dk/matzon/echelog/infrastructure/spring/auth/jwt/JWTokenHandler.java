package dk.matzon.echelog.infrastructure.spring.auth.jwt;

import dk.matzon.echelog.infrastructure.spring.auth.UserService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class JWTokenHandler implements dk.matzon.echelog.infrastructure.spring.auth.TokenHandler {

    @Value("${echelog.token.secret}")
    private String secret;

    private String base64EncodedSecret;
    private UserService userService;

    @Autowired
    public JWTokenHandler(UserService _userService) {
        userService = _userService;
    }

    @PostConstruct
    public void init() {
        base64EncodedSecret = Base64Codec.BASE64.encode(secret.getBytes());
    }

    @Override
    public User parseUserFromToken(String _token) {
        String username = null;
        try {
            username = Jwts.parser()
                    .setSigningKey(base64EncodedSecret)
                    .parseClaimsJws(_token)
                    .getBody()
                    .getSubject();
        } catch (JwtException je) {
            return null;
        } catch (RuntimeException re) {
            return null;
        }
        return userService.loadUserByUsername(username);
    }

    @Override
    public String createTokenForUser(User _user) {
        long validityInMillis = TimeUnit.HOURS.toMillis(1l);
        Date expiry = new Date(new Date().getTime() + validityInMillis);
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(_user.getUsername())
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, base64EncodedSecret)
                .compact();
    }
}