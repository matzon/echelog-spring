package dk.matzon.echelog.interfaces.rest;

import dk.matzon.echelog.infrastructure.spring.auth.TokenAuthenticationService;
import dk.matzon.echelog.infrastructure.spring.auth.UserService;
import dk.matzon.echelog.infrastructure.spring.auth.jwt.JWToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Rest access point for Echelog application
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
@RestController
@RequestMapping(path = "/", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class AuthenticationController {

    private UserService userService;
    private TokenAuthenticationService tokenAuthenticationService;

    public AuthenticationController() {
    }

    @Autowired
    public AuthenticationController(UserService userService, TokenAuthenticationService _tokenAuthenticationService) {
        this.userService = userService;
        tokenAuthenticationService = _tokenAuthenticationService;
    }

    @RequestMapping(path = "/login/{key}", method = RequestMethod.POST)
    public ResponseEntity<User> login(HttpServletResponse _response, @PathVariable("key") String _apiKey) {
        User user = userService.loadUserByUsername(_apiKey);
        if (user != null) {
            tokenAuthenticationService.addAuthentication(_response, new JWToken(user, true));
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>((User) null, HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public ResponseEntity<User> logout() {
        /* how ? */
        return new ResponseEntity<User>((User) null, HttpStatus.OK);
    }

}