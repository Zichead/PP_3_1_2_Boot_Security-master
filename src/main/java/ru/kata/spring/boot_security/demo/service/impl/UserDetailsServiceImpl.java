package ru.kata.spring.boot_security.demo.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInDb = Optional.ofNullable(userRepository.findByUsername(username));
        if (userInDb.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден.");
        }
        return userInDb.get();
    }
}
