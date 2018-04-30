package com.stanleynackademin.todo.resource;

import com.stanleynackademin.todo.model.User;
import com.stanleynackademin.todo.service.UserService;
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
@Path("users")
public final class UserResource {

    @Context
    UriInfo uriInfo;

    @Context
    HttpHeaders headers;

    private final UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    // TODO: 2018-04-28 Add filter?
    @POST
    public Response addUser(User user) {
        User result = service.addUser(user);

        return Response.created(URI.create(uriInfo
                .getAbsolutePathBuilder()
                .path(result.getId().toString())
                .toString()))
                .build();
    }

    @GET
    @Path("{id}")
    public Response findUser(@PathParam("id") Long id) {
        return service.findUser(id)
                .map(c -> Response.ok(c))
                .orElse(Response.status(NOT_FOUND))
                .build();
    }

    @GET
    public Response getAll() {
        return Response.ok(service.getAllUsers()).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        return service.removeUser(id)
                .map(u -> Response.noContent())
                .orElse(Response.status(NOT_FOUND))
                .build();
    }
}
