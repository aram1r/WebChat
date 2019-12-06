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
@SessionAttributes("userFTL")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/auth/sign_up";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

//    @RequestMapping("/login")
//    public String login(@RequestParam(name="error", required=false) Boolean error, Model model) {
//        if (Boolean.TRUE.equals(error)) {
//            model.addAttribute("error", true);
//        }
//        return "/auth/sign_in";
//    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(name="error", required=false) Boolean error, Model model, @ModelAttribute("userFTL") User user) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userFTL", user.getLogin());
        modelAndView.setViewName("/auth/sign_in");
        return modelAndView;
    }

    @ModelAttribute("userFTL")
    public User createUser() {
        return new User();
    }
}
