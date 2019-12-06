package system.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import system.dao.UserDAO;
import system.model.User;

import java.util.List;

@Component
public class JdbcTemplateUserDAO implements UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query(
                "select * from test.users",
                new BeanPropertyRowMapper<>(User.class));
    }

    public User getUser(String login) {
        return jdbcTemplate.query("select * from test.users where login = ?",
                new Object[] {login},
                new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void addUser(User user) {
        jdbcTemplate.update("INSERT INTO test.users (login, password, name, surname) VALUES (?,?,?,?)",
                user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
    }

//    public void removeUser(String login) {
//        jdbcTemplate.update("DELETE FROM test.users WHERE id = ?", login);
//    }
//
//    public void updatePassword(String login, String password) {
//        jdbcTemplate.update("UPDATE test.users SET password = ? WHERE login = ?", login, password);
//    }
}
