package com.stanleynackademin.todo.model.dto;

public final class TodoDto {

    private Long id;
    private String description;
    private String priority;

    public TodoDto(Long id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id:%d, description:'%s', priority:'%s'",
                id, description, priority
        );
    }
}
