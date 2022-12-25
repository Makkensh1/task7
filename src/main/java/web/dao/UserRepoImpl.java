package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl implements UserRepo {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserRepoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNativeQuery("insert into users values (name = ?1, email= ?2, last_name =?3)");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getEmail());
        query.setParameter(3, user.getLastName());
    }

    @Override
    public List<User> listUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
    @Modifying
    public void update(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("update users set name = ?1, email = ?2, last_name = ?3 where id = ?4");
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getEmail());
        query.setParameter(3, user.getLastName());
        query.setParameter(4, user.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    @Modifying
    public void deleteUserById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("delete from users where id=?1");
        query.setParameter(1, id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

}
