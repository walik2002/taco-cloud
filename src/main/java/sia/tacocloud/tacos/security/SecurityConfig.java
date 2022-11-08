package sia.tacocloud.tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.tacos.models.User;
import sia.tacocloud.tacos.repos.UserRepository;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();//надежное шифрование bcrypt;
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            User user = userRepo.findByUsername(username);

            if(user!=null)
                return user;

            throw  new UsernameNotFoundException("User " + username + " not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain ( HttpSecurity http) throws Exception{
         /*
            Метод filterChain() принимает объект HttpSecurity, который действует как построитель,
             который можно использовать для настройки работы системы безопасности на веб-уровне.
             После настройки конфигурации безопасности с помощью объекта HttpSecurity вызов
             build() создаст компонент SecurityFilterChain.
             Помимо всего прочего, с помощью HttpSecurity можно:
             - потребовать выполнения определенных условий безопасности перед обслуживанием запроса;
             - отправить пользователю свою страницу входа;
             - предоставить пользователям возможности выйти из приложения;
             - настроить защиту от подделки межсайтовых запросов
        */
        return http
                .authorizeRequests()            /*authorizeRequests() возвращает объект ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry,
                                                  с помощью которого можно задать пути и шаблоны URL, а также требования безопасности для этих путей*/
                    //.antMatchers(HttpMethod.POST,"/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/design","/orders").access("hasRole('USER')")//SpEl
                    .antMatchers("/","/**").access("permitAll")//все остальные запросы должны обрабатываться безоговорочно
                .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design",true)
                .and()
                    .logout()
                      .logoutSuccessUrl("/")
                .and()
                    .csrf()
                        .ignoringAntMatchers("/h2-console/**")
                .and()
                    .headers()
                        .frameOptions()
                            .sameOrigin()
                .and()
                    .build();
                //ПОРЯДОК ИМЕЕТ ЗНАЧЕНИЕ!!
    }

}
