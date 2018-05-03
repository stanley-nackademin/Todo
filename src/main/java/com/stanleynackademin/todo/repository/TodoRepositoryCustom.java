package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.User;

import java.util.Optional;

public interface TodoRepositoryCustom {

    Optional<User> findByUser_Id(Long id);
}
