package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {

    List<Todo> findAllByUser_Id(Long id);

    List<Todo> findAllByUser_IdAndPriority(Long id, String priority);
}
