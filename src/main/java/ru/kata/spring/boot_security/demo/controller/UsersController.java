package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UsersController {

//    private static final String REDIRECT = "redirect:/users";

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    //    @GetMapping()
//    public String allUsers (Model model) {
//        model.addAttribute("user", userService.getUserList());
//        return "index";
//    }
//
    @GetMapping("")
    public String show (Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userRoles", user.getAuthorities());
        return "user";
    }
//
//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "new";
//    }
//
//    @PostMapping()
//    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()){
//            return "new";
//        }
//        userService.add(user);
//        return REDIRECT;
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.findUserToID(id));
//        return "edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
//        userService.updateUser(user);
//        return REDIRECT;
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return REDIRECT;
//    }
}
