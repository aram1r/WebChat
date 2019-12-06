package system.bean;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@SessionScope
@Component
public class HttpSessionBean {

    private String login;
    private boolean online = false;

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
    public void setOnline() {
        this.online = true;
    }
    public void setOffline() {
        this.online = false;
    }
}
