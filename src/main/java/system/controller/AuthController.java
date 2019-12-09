package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import system.model.User;
import system.services.UserService;
import system.util.UserValidator;
import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String getSignUp(Model model) {
        return "/auth/sign_up";
    }


    @PostMapping("/registration")
    public String signUp(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {

        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/registration";
        }
        model.addAttribute("login", user.getLogin());
        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(name="error", required=false) Boolean error, Model model, @ModelAttribute("user") User user) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth/sign_in");
        return modelAndView;
    }


    @ModelAttribute("user")
    public User createUser() {
        return new User();
    }
}
