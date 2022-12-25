package web.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserRepo;
import web.model.User;

import java.util.List;

@Service
@Qualifier("us")
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userRepo.addUser(user);
    }

    @Override
    public List<User> listUsers() {
        return userRepo.listUsers();
    }

    @Override
    @Transactional
    public void update(User user) {
        userRepo.update(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepo.deleteUserById(id);
    }

}
