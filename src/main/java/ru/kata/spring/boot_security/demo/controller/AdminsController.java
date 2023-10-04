package ru.kata.spring.boot_security.demo.controller;


import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final RoleService roleService;

    private final UserService userService;

    private static final String REDIRECT = "redirect:/admin";

    public AdminsController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("")
    public String allUsers (Model model) {
        model.addAttribute("user", userService.getUserList());
        return "admin";
    }


    @GetMapping("/{id}")
    public String show (@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserToID(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser( User user, Model model) {
        model.addAttribute("roles", roleService.allRoles());
        return "new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("userRoles", roleService.allRoles());
            return "new";
        }
        userService.add(user);
        return REDIRECT;
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findUserToID(id));
        model.addAttribute("userRoles", roleService.allRoles());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRoles", roleService.allRoles());
            return "edit";
        }
        userService.updateUser(user);
        return REDIRECT;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return REDIRECT;
    }
}
