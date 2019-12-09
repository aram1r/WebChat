package system.services;
import system.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(String login);

    void loginEvent(User user);

    String getLoggedUser();

    void addUser(User user);
}
