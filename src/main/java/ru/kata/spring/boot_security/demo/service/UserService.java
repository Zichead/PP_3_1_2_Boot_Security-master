package ru.kata.spring.boot_security.demo.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    boolean add(User user);

    User findUserToID(Long id);

    void deleteUser (Long id);

    void updateUser (User user);

    List<User> getUserList();
}
