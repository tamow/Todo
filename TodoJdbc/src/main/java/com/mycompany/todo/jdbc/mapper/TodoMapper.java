package com.mycompany.todo.jdbc.mapper;

import com.mycompany.todo.jdbc.entiry.Todo;
import java.util.List;

/**
 *
 * @author ta_watanabe
 */
public interface TodoMapper {

    public List<Todo> getList();

    public int add(Todo todo);

    public int update(Todo todo);

    public int delete(Integer id);
}
