package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.RoleServiceImpl;
import ru.itmentor.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    public RESTController(UserServiceImpl userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsersAndRoles();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUsersAndRoles().get(id);
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleServiceImpl.findAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable("id") int id) {
        return roleServiceImpl.findAllRoles().get(id);
    }
}
