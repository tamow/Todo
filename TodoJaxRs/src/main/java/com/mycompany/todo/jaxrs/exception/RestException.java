package com.mycompany.todo.jaxrs.exception;

import com.mycompany.todo.jaxrs.dto.ErrorDto;
import java.util.List;
import javax.ws.rs.core.Response.StatusType;

/**
 *
 * @author ta_watanabe
 */
public class RestException extends RuntimeException {

    private ErrorDto errorDto;

    public RestException(StatusType statusType, String... errorMessages) {
        this.errorDto = new ErrorDto(statusType, errorMessages);
    }

    public RestException(StatusType statusType, List<String> errorMessageList) {
        this(statusType, errorMessageList.toArray(new String[errorMessageList.size()]));
    }

    public ErrorDto getErrorDto() {
        return this.errorDto;
    }

}
