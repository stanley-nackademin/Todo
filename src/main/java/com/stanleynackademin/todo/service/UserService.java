package com.stanleynackademin.todo.service;

import com.stanleynackademin.todo.model.User;
import com.stanleynackademin.todo.repository.UserRepository;
import com.stanleynackademin.todo.service.exception.InvalidUserException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User user) {
        if (!isValidUser(user)) {
            throw new InvalidUserException("First or last name can not be empty");
        }

        String firstName = user.getFirstName().trim();
        String lastName = user.getLastName().trim();

        User newUser = new User(firstName, lastName);
        return repository.save(newUser);
    }

    public Optional<User> findUser(Long id) {
        return repository.findById(id);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        repository.findAll().forEach(users::add);
        return users;
    }

    public void removeUser(Long id) {
        repository.deleteById(id);
    }

    private boolean isValidUser(User user) {
        if (user.getFirstName() == null || user.getLastName() == null) {
            return false;
        } else if (user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
