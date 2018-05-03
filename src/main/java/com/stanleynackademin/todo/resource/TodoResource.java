package com.stanleynackademin.todo.resource;

import com.stanleynackademin.todo.model.Todo;
import com.stanleynackademin.todo.service.TodoService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;

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

    @GET
    @Path("{id}")
    public Response findTodo(@PathParam("id") Long id) {
        return service.findTodo(id)
                .map(t -> Response.ok(t))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public Response getAll(@QueryParam("user") @DefaultValue("0") Long id,
                           @QueryParam("priority") @DefaultValue("all") String priority) {

        return Response.ok(service.getAllTodos(id, priority)).build();
    }

    @PUT
    @Path("{id}")
    public Response assignTodo(@QueryParam("user") @DefaultValue("0") Long userId,
                           @PathParam("id") Long id) {
        return service.updateTodo(id, userId)
                .map(t -> Response.noContent())
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTodo(@PathParam("id") Long id) {
        return service.removeTodo(id)
                .map(t -> Response.noContent())
                .orElse(Response.status(NOT_FOUND))
                .build();
    }
}
