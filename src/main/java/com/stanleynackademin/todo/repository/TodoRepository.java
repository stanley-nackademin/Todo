package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
