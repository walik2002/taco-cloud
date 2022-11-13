package sia.tacocloud.tacos.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.models.Ingredient.Type;
import sia.tacocloud.tacos.models.Taco;
import sia.tacocloud.tacos.models.User;
import sia.tacocloud.tacos.repos.IngredientRepository;
import sia.tacocloud.tacos.repos.TacoRepository;
import sia.tacocloud.tacos.repos.UserRepository;
import sia.tacocloud.tacos.security.SecurityConfig;

import java.util.Arrays;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {



    @Bean
    public CommandLineRunner dataLoader(IngredientRepository repo,
                                        UserRepository userRepo,
                                        TacoRepository tacoRepository) { // user repo for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                repo.deleteAll();
                userRepo.deleteAll();

                Ingredient flourTortilla = new Ingredient(
                        "FLTO", "Flour Tortilla", Type.WRAP);
                Ingredient cornTortilla = new Ingredient(
                        "COTO", "Corn Tortilla", Type.WRAP);
                Ingredient groundBeef = new Ingredient(
                        "GRBF", "Ground Beef", Type.PROTEIN);
                Ingredient carnitas = new Ingredient(
                        "CARN", "Carnitas", Type.PROTEIN);
                Ingredient tomatoes = new Ingredient(
                        "TMTO", "Diced Tomatoes", Type.VEGGIES);
                Ingredient lettuce = new Ingredient(
                        "LETC", "Lettuce", Type.VEGGIES);
                Ingredient cheddar = new Ingredient(
                        "CHED", "Cheddar", Type.CHEESE);
                Ingredient jack = new Ingredient(
                        "JACK", "Monterrey Jack", Type.CHEESE);
                Ingredient salsa = new Ingredient(
                        "SLSA", "Salsa", Type.SAUCE);
                Ingredient sourCream = new Ingredient(
                        "SRCR", "Sour Cream", Type.SAUCE);
                repo.save(flourTortilla);
                repo.save(cornTortilla);
                repo.save(groundBeef);
                repo.save(carnitas);
                repo.save(tomatoes);
                repo.save(lettuce);
                repo.save(cheddar);
                repo.save(jack);
                repo.save(salsa);
                repo.save(sourCream);
                Taco taco1 = new Taco();
                taco1.setName("Carnivore");
                taco1.setIngredients(Arrays.asList(
                        flourTortilla, groundBeef, carnitas,
                        sourCream, salsa, cheddar));
                 tacoRepository.save(taco1);
                Taco taco2 = new Taco();
                taco2.setName("Bovine Bounty");
                taco2.setIngredients(Arrays.asList(
                        cornTortilla, groundBeef, cheddar,
                        jack, sourCream));
                tacoRepository.save(taco2);
                Taco taco3 = new Taco();
                taco3.setName("Veg-Out");
                taco3.setIngredients(Arrays.asList(
                        flourTortilla, cornTortilla, tomatoes,
                        lettuce, salsa));
                tacoRepository.save(taco3);

                /*userRepo.save(new User("walentin",encoder.encode("1234"),"Walentin","st.Leninskaya",
                        "Mogilev","Belarus","211030","+375333212233"));*/
            }
        };
    }
}
