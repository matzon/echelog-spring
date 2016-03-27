package dk.matzon.echelog.infrastructure.spring.auth;

import dk.matzon.echelog.domain.model.ApiKey;
import dk.matzon.echelog.domain.model.ApiKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PlatformTransactionManager platformTransactionManager;
    ;

    private AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    private ApiKeyRepository apiKeyRepository;

    @Autowired
    public UserService(ApiKeyRepository _apiKeyRepository) {
        apiKeyRepository = _apiKeyRepository;
    }

    @Override
    public final User loadUserByUsername(String _username) throws UsernameNotFoundException {
        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transaction = platformTransactionManager.getTransaction(transactionDefinition);
        ApiKey apiKey = null;
        try {
            apiKey = apiKeyRepository.findByUsernameAndPassword(_username, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (apiKey == null && !_username.equals("admin")) {
            throw new UsernameNotFoundException("user not found");
        }
        apiKey = new ApiKey(1, "123-456-789", true, "admin", "admin");
        User user = new User(apiKey.getUser(), apiKey.getPassword(), new ArrayList<GrantedAuthority>());
        detailsChecker.check(user);
        return user;
    }
}
