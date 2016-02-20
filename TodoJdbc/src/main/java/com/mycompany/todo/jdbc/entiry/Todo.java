package com.mycompany.todo.jdbc.entiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dokyou3
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo {

    private Integer id;

    private String title;

    private String memo;

}
