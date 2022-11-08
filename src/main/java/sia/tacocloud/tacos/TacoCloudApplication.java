package sia.tacocloud.tacos;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sia.tacocloud.tacos.models.Ingredient;
import sia.tacocloud.tacos.models.User;
import sia.tacocloud.tacos.repos.IngredientRepository;
import sia.tacocloud.tacos.models.Ingredient.Type;
import sia.tacocloud.tacos.repos.UserRepository;
import sia.tacocloud.tacos.security.SecurityConfig;

import java.util.*;

@SpringBootApplication
public class TacoCloudApplication implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
