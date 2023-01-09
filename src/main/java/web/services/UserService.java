package web.services;

import org.springframework.stereotype.Service;
import web.model.User;

import java.util.List;

@Service
public interface UserService {

    User findById(Long id);
    void addUser(User user);

    List<User> listUsers();

    void update(User user);

    void deleteUserById(Long id);
}
