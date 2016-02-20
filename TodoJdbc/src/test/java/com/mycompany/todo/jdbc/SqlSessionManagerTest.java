package com.mycompany.todo.jdbc;

import java.io.IOException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author ta_watanabe
 */
public class SqlSessionManagerTest {

    private final SqlSessionFactory sqlSessionFactory;

    private static final SqlSessionManagerTest sqlSessionManager = new SqlSessionManagerTest();

    private SqlSessionManagerTest() {
        sqlSessionFactory = createSqlSessionFactory();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionManager.sqlSessionFactory;
    }

    private SqlSessionFactory createSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"), "todo_test");
        } catch (IOException e) {
        }
        return sessionFactory;
    }
}
