package com.stanleynackademin.todo.repository;

import com.stanleynackademin.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long>, TodoRepositoryCustom {

    List<Todo> findAllByUser_Id(Long id);

    List<Todo> findAllByUser_IdAndPriority(Long id, String priority);
}
