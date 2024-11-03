package ru.itmentor.spring.boot_security.demo.services;

import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    void createUser(User user);

    User readUser(int id);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> getUsersAndRoles();
}

