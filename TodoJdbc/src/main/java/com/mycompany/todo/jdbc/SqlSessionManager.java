package com.mycompany.todo.jdbc;

import java.io.IOException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 *
 * @author ta_watanabe
 */
public class SqlSessionManager {

    private final SqlSessionFactory sqlSessionFactory;

    private static final SqlSessionManager sqlSessionManager = new SqlSessionManager();

    private SqlSessionManager() {
        sqlSessionFactory = createSqlSessionFactory();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionManager.sqlSessionFactory;
    }

    private SqlSessionFactory createSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
        }
        return sessionFactory;
    }
}
