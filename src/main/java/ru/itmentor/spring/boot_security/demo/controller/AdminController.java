package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.services.RoleServiceImpl;
import ru.itmentor.spring.boot_security.demo.services.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private void addAllRolesToModel(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
    }

    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> users = userService.getUsersAndRoles();
        model.addAttribute("users", users);
        return "admin/all";
    }

    @GetMapping( "/{id}")
    public String readUser(@PathVariable("id")int id , Model model) {
        model.addAttribute("user",userService.readUser(id));
        return "admin/show";
    }

    @GetMapping("admin/createUser")
    public String showCreateForm(Model model) {
        addAllRolesToModel(model);
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/editUser/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.readUser(id);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String master(@ModelAttribute ("user") User user, @PathVariable("id") int id) {
        userService.master(id,user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
