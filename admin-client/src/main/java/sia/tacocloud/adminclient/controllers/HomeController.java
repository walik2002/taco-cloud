package sia.tacocloud.adminclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Principal principal) {
        return "home";
    }
}
