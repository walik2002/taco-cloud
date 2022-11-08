package sia.tacocloud.tacos.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.models.Ingredient.Type;
import sia.tacocloud.tacos.models.User;
import sia.tacocloud.tacos.repos.IngredientRepository;
import sia.tacocloud.tacos.repos.UserRepository;
import sia.tacocloud.tacos.security.SecurityConfig;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {



    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo, PasswordEncoder encoder) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

                repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
                repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
                repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
                repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
                repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
                repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
                repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
                repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

                userRepo.save(new User("walentin",encoder.encode("1234"),"Walentin","st.Leninskaya",
                        "Mogilev","Belarus","211030","+375333212233"));
            }
        };
    }
}
