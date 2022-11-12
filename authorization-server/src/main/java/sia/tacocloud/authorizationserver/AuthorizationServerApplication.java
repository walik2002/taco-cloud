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
    public ApplicationRunner dataLoader(
            UserRepository userRepository, PasswordEncoder passwordEncoder
    ){
        return args -> {
            userRepository.save(
                    new User("walentin", passwordEncoder.encode("1234"), "ROLE_ADMIN" )
            );
            userRepository.save(
                    new User("tacocloud", passwordEncoder.encode("1234"), "ROLE_ADMIN" )
            );
        };
    }
}
