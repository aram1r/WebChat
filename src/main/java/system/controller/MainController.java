package system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import system.bean.HttpSessionBean;
import system.model.User;
import system.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("nameeeee");
        return "/chat";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/users";
    }

}
