package com.stanleynackademin.todo.model;

import javax.persistence.*;

@Entity
public final class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Todo() {}

    public Todo(String description, String priority) {
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

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id:%d, description='%s', priority='%s'",
                id, description, priority
        );
    }
}
