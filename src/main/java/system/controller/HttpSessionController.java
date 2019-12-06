package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import system.bean.HttpSessionBean;

@RestController
public class HttpSessionController {
    private final HttpSessionBean httpSessionBean;

    @Autowired
    public HttpSessionController(HttpSessionBean httpSessionBean) {
        this.httpSessionBean = httpSessionBean;
    }

    @GetMapping(path="/controller")
    public String example(String login) {
        if (!StringUtils.isEmpty(login)) {
            httpSessionBean.setLogin(login);
        } else if (!StringUtils.isEmpty(httpSessionBean.getLogin())) {
            return "Current name:" + httpSessionBean.getLogin();
        } else {
            return "There is no saved name";
        }
        return login;
    }
}
