package health_center.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginCtrl {

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        if ("admin".equals(username) && "password".equals(password)) {
            return "redirect:/home";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
