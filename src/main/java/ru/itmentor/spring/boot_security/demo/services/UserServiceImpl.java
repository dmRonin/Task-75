package ru.itmentor.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void createUser(User user) {
        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    @Override
    public User readUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userRepository.save(user);

    }

    @Transactional
     public void master(int id,User updatedUser) {
        updatedUser.setId(id);
        userRepository.save(updatedUser);

    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);

    }

    @Override
    public List<User> getUsersAndRoles() {
        return userRepository.listUsersAndRoles();
    }
}
