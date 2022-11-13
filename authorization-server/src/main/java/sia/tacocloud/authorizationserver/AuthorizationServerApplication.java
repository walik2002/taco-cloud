package sia.tacocloud.authorizationserver;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.authorizationserver.models.User;
import sia.tacocloud.authorizationserver.repos.UserRepository;

@SpringBootApplication
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Bean
    public ApplicationRunner dataLoader(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(new User(1L, "a1", encoder.encode("1"), "ROLE_ADMIN"));
            repo.save(new User(2L, "a2", encoder.encode("1"), "ROLE_ADMIN"));
        };
    }
}
