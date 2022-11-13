package sia.tacocloud.authorizationserver.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.authorizationserver.repos.UserRepository;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        return http
                //设置了<iframe>标签的“同源策略”，解决了上面出现的问题
                //https://blog.csdn.net/weixin_43981241/article/details/108240756
                .headers().frameOptions().sameOrigin()
                .and()
                //使得h2 console页面不去做csrf检查
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
                .and()
                .authorizeRequests()
                //使得访问h2 console时，不需要先登录系统
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().anonymous())
                .formLogin()
                .and()
                .build();
        // @formatter:on

    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> userRepo.findByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}