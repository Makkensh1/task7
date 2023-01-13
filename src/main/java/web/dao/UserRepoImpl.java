package web.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Modifying
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    public User findUserByID(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Modifying
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    @Modifying
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

}
