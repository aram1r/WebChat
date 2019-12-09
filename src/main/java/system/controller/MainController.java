package system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@SessionAttributes("user")
public class MainController {

    @Autowired
    ChatController chatController;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/chat")
    public ModelAndView chat(ModelAndView modelAndView, Principal principal) {
        String data = principal.getName();
        modelAndView.addObject("login", parsePrincipal(data));
        modelAndView.setViewName("/chat");
        return modelAndView;
    }

    private String parsePrincipal(String data) {
        int coma = data.indexOf(",");
        data = data.substring(coma+1).trim();
        coma = data.indexOf(",");
        String login = data.substring(0, coma+1).trim().replaceAll("login=", "").replaceAll("'", "").replaceAll(",", "").trim();
        return login;
    }
}
