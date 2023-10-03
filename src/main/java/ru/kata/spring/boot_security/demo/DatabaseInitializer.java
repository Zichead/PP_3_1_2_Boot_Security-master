package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

//ApplicationListener - интерфейс, который позволяет обрабатывать ApplicationEvent события
//ContextRefreshedEvent - публикуется автоматически после поднятия контекста
@Component
public class DatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Создали роль юзер
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        //Создали роль админ
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        List<Role> userRoles = List.of(userRole);
        List<Role> adminRoles = Arrays.asList(adminRole, userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setName("lfksdf");
        admin.setEmail("sdkfh@gmai.com");
        admin.setSurname("dfksdfk");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setName("lfksdf");
        user.setEmail("sdkfh@gmai.com");
        user.setSurname("dfksdfk");
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(userRoles);
        userRepository.save(user);

        User user2 = new User();
        user2.setUsername("user2");
        user2.setName("lfksdf");
        user2.setEmail("sdkfh@gmai.com");
        user2.setSurname("dfksdfk");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setRoles(userRoles);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("user3");
        user3.setName("lfksdf");
        user3.setEmail("sdkfh@gmai.com");
        user3.setSurname("dfksdfk");
        user3.setPassword(passwordEncoder.encode("user3"));
        user3.setRoles(userRoles);
        userRepository.save(user3);
    }
}