package system.dao;

import org.springframework.stereotype.Component;
import system.model.User;
import java.util.List;


@Component
public interface UserDAO {
    List<User> getAllUsers();

    User getUser(String login);

    void addUser(User user);


}
