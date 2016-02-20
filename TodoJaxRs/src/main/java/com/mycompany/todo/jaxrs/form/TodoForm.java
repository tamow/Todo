package com.mycompany.todo.jaxrs.form;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ta_watanabe
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoForm {

    private String id;

    @NotNull(message = "titleが未指定")
    private String title;

    @NotNull(message = "memoが未指定")
    private String memo;
}
