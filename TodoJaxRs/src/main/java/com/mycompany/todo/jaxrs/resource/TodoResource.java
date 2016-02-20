package com.mycompany.todo.jaxrs.resource;

import com.mycompany.todo.jaxrs.form.TodoForm;
import com.mycompany.todo.jdbc.SqlSessionManager;
import com.mycompany.todo.jdbc.entiry.Todo;
import com.mycompany.todo.jdbc.mapper.TodoMapper;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.noContent;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 *
 * @author ta_watanabe
 */
@Path("/api")
@Produces(APPLICATION_JSON)
public class TodoResource {

    @Context
    private UriInfo uriInfo;

    @Path("/list")
    @GET
    public Response list() throws Exception {
        SqlSessionFactory factory = SqlSessionManager.getSqlSessionFactory();
        if (factory == null) {
            throw new IOException();
        }
        try (SqlSession session = factory.openSession()) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            List<Todo> list = mapper.getList();
            return Response.ok(list).build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response add(TodoForm todoForm) throws Exception {
        SqlSessionFactory factory = SqlSessionManager.getSqlSessionFactory();
        if (factory == null) {
            throw new IOException();
        }
        try (SqlSession session = factory.openSession(true)) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            Todo todo = new Todo();
            BeanUtils.copyProperties(todo, todoForm);
            mapper.add(todo);
            URI location = uriInfo.getAbsolutePathBuilder().path(todo.getId().toString()).build();
            return created(location).build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response update(TodoForm todoForm) throws Exception {
        SqlSessionFactory factory = SqlSessionManager.getSqlSessionFactory();
        if (factory == null) {
            throw new IOException();
        }
        try (SqlSession session = factory.openSession(true)) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            Todo todo = new Todo();
            BeanUtils.copyProperties(todo, todoForm);
            mapper.update(todo);
            URI location = uriInfo.getAbsolutePathBuilder().path(todo.getId().toString()).build();
            return created(location).build();
        }
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") String id) throws Exception {
        SqlSessionFactory factory = SqlSessionManager.getSqlSessionFactory();
        if (factory == null) {
            throw new IOException();
        }
        try (SqlSession session = factory.openSession(true)) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            mapper.delete(Integer.parseInt(id));
            URI location = this.uriInfo.getAbsolutePath();
            return noContent().location(location).build();
        }
    }

}
