package com.stanleynackademin.todo.resource.mapper;

import com.stanleynackademin.todo.service.exception.InvalidUserException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.Collections;

import static javax.ws.rs.core.Response.Status.*;

@Provider
public final class InvalidUserMapper implements ExceptionMapper<InvalidUserException> {

    @Override
    public Response toResponse(InvalidUserException e) {
        return Response.status(BAD_REQUEST).entity(Collections.singletonMap("error", e.getMessage())).build();
    }
}
