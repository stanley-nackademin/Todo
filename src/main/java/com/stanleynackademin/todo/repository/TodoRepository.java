package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>, TodoRepositoryCustom {

    List<Todo> findAllByUserId(Long id);

    List<Todo> findAllByUserIdAndPriority(Long id, String priority);
}
