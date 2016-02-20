package com.mycompany.todo.jaxrs.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.ws.rs.core.Response.StatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ta_watanabe
 */
@NoArgsConstructor
public class ErrorDto {

    @Getter
    @Setter
    @JsonProperty("error_type")
    private StatusType statusType;

    @Getter
    @Setter
    @JsonProperty("error_messages")
    private String[] strings;

    public ErrorDto(StatusType statusType, String... strings) {
        this.statusType = statusType;
        this.strings = strings;
    }
}
