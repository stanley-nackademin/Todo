package com.stanleynackademin.todo.resource;

import com.stanleynackademin.todo.model.Todo;
import com.stanleynackademin.todo.service.TodoService;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Path("todos")
public final class TodoResource {

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders headers;

    private final TodoService service;

    public TodoResource(TodoService service) {
        this.service = service;
    }

    @POST
    public Response addTodo(Todo todo) {
        Todo result = service.addTodo(todo);

        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getId().toString())
                .toString()))
                .build();
    }
}
