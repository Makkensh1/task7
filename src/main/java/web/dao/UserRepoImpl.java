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
        Query query = entityManager.createNativeQuery("select  * from users");
        List<User> users = new ArrayList<>();
        List<Object[]> results = query.getResultList();
        results.stream().forEach(e -> System.out.println(e));
        for (Object[] result : results) {
            BigInteger id = (BigInteger) result[0];
            users.add(new User((id.longValue()), (String) result[1], (String) result[2], (String) result[3]));
        }
        users.stream().forEach(e -> System.out.println(e));
        return users;
    }

    @Override
    public User findByID(Long id) {
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
