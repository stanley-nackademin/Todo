package com.stanleynackademin.todo.service;

import com.stanleynackademin.todo.model.User;
import com.stanleynackademin.todo.repository.UserRepository;
import com.stanleynackademin.todo.service.exception.InvalidUserException;
import org.springframework.stereotype.Service;

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

        return repository.save(new User(firstName, lastName));
    }

    public Optional<User> findUser(Long id) {
        return repository.findById(id);
    }

    public List<User> getAllUsers() {

        return repository.findAll();
    }

    public Optional<User> removeUser(Long id) {
        Optional<User> result = repository.findById(id);

        if (result.isPresent()) {
            repository.deleteById(id);
        }

        return result;
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
