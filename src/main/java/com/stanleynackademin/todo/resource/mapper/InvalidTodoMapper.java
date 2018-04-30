package com.stanleynackademin.todo.resource.mapper;

import com.stanleynackademin.todo.service.exception.InvalidTodoException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Collections;

import static javax.ws.rs.core.Response.Status.*;

@Provider
public final class InvalidTodoMapper implements ExceptionMapper<InvalidTodoException> {

    @Override
    public Response toResponse(InvalidTodoException e) {
        return Response.status(BAD_REQUEST).entity(Collections.singletonMap("error", e.getMessage())).build();
    }
}
