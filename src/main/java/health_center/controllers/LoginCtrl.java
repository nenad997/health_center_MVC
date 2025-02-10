package health_center.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginCtrl {

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }
}
