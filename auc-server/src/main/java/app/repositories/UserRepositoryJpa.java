package app.repositories;

import app.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryJpa extends AbstractEntityRepositoryJPA<User> {
    @PersistenceContext
    private EntityManager entityManager;

    public UserRepositoryJpa() {
        super(User.class);
    }

    public List<User> findAll() {
        TypedQuery<User> query =
                entityManager.createQuery(
                        "SELECT user FROM User user", User.class);

        return query.getResultList();
    }

    public User findById(long id) {
        return entityManager.find(User.class, id);
    }

    public User save(User user) {
        return entityManager.merge(user);
    }

    public User deleteById(long id) {
        User user = findById(id);

        if (user == null) return null;

        entityManager.remove(user);
        return user;
    }
}
