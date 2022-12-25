package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import java.util.List;

public interface UserRepo {

     void addUser(User user);

    List<User> listUsers();

    @Transactional
    void update(User user);

    @Transactional
    void deleteUserById(Long id);
}
