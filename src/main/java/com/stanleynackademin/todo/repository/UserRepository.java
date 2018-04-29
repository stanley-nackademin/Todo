package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
