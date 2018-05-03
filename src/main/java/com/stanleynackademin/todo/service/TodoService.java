package com.stanleynackademin.todo.service;

import com.stanleynackademin.todo.model.Todo;
import com.stanleynackademin.todo.model.User;
import com.stanleynackademin.todo.model.dto.TodoDto;
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

    public List<TodoDto> getAllTodos(Long id, String priority) {
        List<TodoDto> todos = new ArrayList<>();

        if (id == 0) {
            repository.findAll().forEach(t -> todos.add(convertToDto(t)));
        } else if (!(id == 0)) {
            if (priority.equals("all")) {
                repository.findAllByUser_Id(id).forEach(t -> todos.add(convertToDto(t)));
            } else {
                repository.findAllByUser_IdAndPriority(id, priority).forEach(t -> todos.add(convertToDto(t)));
            }
        }

        return todos;
    }

    public Optional<Todo> updateTodo(Long id, Long userId) {
        Optional<User> user = repository.findByUser_Id(userId);
        Optional<Todo> todo = repository.findById(id);

        if (user.isPresent() && todo.isPresent()) {
            Todo result = todo.get();
            result.assignUser(user.get());
            repository.save(result);

            return todo;
        }

        return Optional.empty();
    }

    public Optional<Todo> removeTodo(Long id) {
        Optional<Todo> result = repository.findById(id);

        if (result.isPresent()) {
            repository.deleteById(id);
        }

        return result;
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

    private TodoDto convertToDto(Todo todo) {
        return new TodoDto(todo.getId(), todo.getDescription(), todo.getPriority());
    }
}
