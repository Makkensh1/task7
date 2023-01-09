package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

public interface UserRepo {

    @Transactional
    void addUser(User user);

    List<User> getAllUsers();

    User findByID(Long id);

    @Transactional
    void update(User user);

    @Transactional
    void deleteUser(User user);
}
