package dk.matzon.echelog.infrastructure.spring.auth;

import dk.matzon.echelog.infrastructure.spring.auth.jwt.JWTAuthenticationProvider;
import dk.matzon.echelog.infrastructure.spring.auth.jwt.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${echelog.token.secret}")
    private String secret;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    private UserService userService;

    public TokenSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(new JWTAuthenticationProvider(tokenAuthenticationService))
                .userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder(13));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // generic config
                .exceptionHandling().and()
                .anonymous().and()
                .servletApi().and()
                .headers().cacheControl().and()
                .and().authorizeRequests()

                // allow authentication
                .antMatchers("/login", "/logout").permitAll()

                // require auth for api
                .antMatchers("/api/**").authenticated().and()

                // filter for token
                .addFilterAfter(new JWTFilter(authenticationManager(), tokenAuthenticationService), AnonymousAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        /* only here to override AuthenticationManagerConfiguration */
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
}
