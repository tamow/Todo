package com.mycompany.todo.jaxrs.provider.error;

import com.mycompany.todo.jaxrs.dto.ErrorDto;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author ta_watanabe
 */
@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {

    @Override
    public Response toResponse(PersistenceException exception) {
        ErrorDto errorDto = new ErrorDto(INTERNAL_SERVER_ERROR, "サーバー内部エラー");
        return status(INTERNAL_SERVER_ERROR).entity(errorDto).build();
    }

}
