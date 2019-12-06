package system.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import system.dao.UserDAO;
import system.model.User;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession () {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAllUsers() {
        return currentSession().createQuery("FROM User ", User.class).list();
    }

    @Override
    public User getUser(String login) {
        Query<User> q = currentSession().createQuery("FROM User WHERE login= :login", User.class);
        q.setParameter("login", login);
        return q.list().stream().findAny().orElse(null);
    }

    public String getNameSurname(String login) {
        Query<User> q = currentSession().createQuery("FROM User WHERE login= :login", User.class);
        q.setParameter("login", login);
        User returnedUser = q.list().stream().findAny().orElse(null);
        return returnedUser.getName()+" "+returnedUser.getSurname();
    }

    @Override
    public void addUser(User user) {
        currentSession().save(user);
    }
}
