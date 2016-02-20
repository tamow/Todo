package com.mycompany.todo.jaxrs.provider.error;

import com.mycompany.todo.jaxrs.dto.ErrorDto;
import com.mycompany.todo.jaxrs.exception.RestException;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ta_watanabe
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<RestException> {

    @Override
    public Response toResponse(RestException exception) {
        ErrorDto errorDto = exception.getErrorDto();
        return status(errorDto.getStatusType()).entity(errorDto).build();
    }
    
}
