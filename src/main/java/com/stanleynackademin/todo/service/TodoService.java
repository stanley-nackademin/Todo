package com.stanleynackademin.todo.service;

import com.stanleynackademin.todo.model.Todo;
import com.stanleynackademin.todo.repository.TodoRepository;
import com.stanleynackademin.todo.service.exception.InvalidTodoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public final class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo addTodo(Todo todo) {
        if (!isValidTodo(todo)) {
            throw new InvalidTodoException("Description or priority can not be empty");
        }

        String description = todo.getDescription().trim();
        String priority = todo.getPriority().trim();

        return repository.save(new Todo(description, priority));
    }

    public Optional<Todo> findTodo(Long id) {
        return repository.findById(id);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        repository.findAll().forEach(todos::add);

        return todos;
    }

    private boolean isValidTodo(Todo todo) {
        if (todo.getDescription() == null || todo.getPriority() == null) {
            return false;
        } else if (todo.getDescription().trim().isEmpty() || todo.getPriority().trim().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
