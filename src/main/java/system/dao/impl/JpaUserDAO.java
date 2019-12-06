package system.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import system.dao.UserDAO;
import system.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User getUser(String login) {
        TypedQuery<User> q = entityManager.createQuery("select u from User u where login = :login", User.class);
        q.setParameter("login", login);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
