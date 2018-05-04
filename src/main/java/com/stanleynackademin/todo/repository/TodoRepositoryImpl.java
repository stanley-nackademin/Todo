package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class TodoRepositoryImpl implements TodoRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<User> findByUserId(Long id) {
        return Optional.ofNullable(manager.find(User.class, id));
    }
}
