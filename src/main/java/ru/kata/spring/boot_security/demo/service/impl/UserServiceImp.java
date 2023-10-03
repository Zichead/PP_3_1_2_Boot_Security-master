package ru.kata.spring.boot_security.demo.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder ;



    @Autowired
    public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isEmpty()){
            throw new UsernameNotFoundException(String.format("User '%s' not found",username));
        }

        return user.get();
    }


    @Transactional
    @Override
    public boolean add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    @Override
    public User findUserToID(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {

//        List<Role> upRoles = user.getRoles();
//        User userForUpdate = findUserToID(user.getId());
//        user.setName(userForUpdate.getName());
//        user.setSurname(userForUpdate.getSurname());
//        user.setEmail(userForUpdate.getEmail());
//        user.setUsername(userForUpdate.getUsername());
//        user.setRoles(upRoles);
//        if (user.getPassword().equals(userForUpdate.getPassword())) {
//            userRepository.save(user);
//        } else {
//            user.setPassword(passwordEncoder.encode(userForUpdate.getPassword()));
//            userRepository.save(user);
//        }
//        userRepository.save(user);


        if (!user.getPassword().equals(findUserToID(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }
}
