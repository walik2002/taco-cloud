package sia.tacocloud.tacos.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.tacos.security.RegistrationForm;
import sia.tacocloud.tacos.repos.UserRepository;

@Controller
@RequestMapping("/register")//будет обрабатывать запросы с путем /register
public class RegistrationController {

    private UserRepository userRepo; //для работы с бд
    private PasswordEncoder passwordEncoder;//шифрование паролей

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return "registration";//возвращает логическое имя представления registration
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        userRepo.save(form.toUser(passwordEncoder));

        return "redirect:/login";
    }
}
