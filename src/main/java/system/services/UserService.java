package system.services;
import system.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUser(String login);

    void addUser(User user);
}
