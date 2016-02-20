package com.mycompany.todo.jdbc.mapper;

import com.mycompany.todo.jdbc.SqlSessionManagerTest;
import com.mycompany.todo.jdbc.entiry.Todo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author ta_watanabe
 */
public class TodoMapperTest {

    private static IDatabaseTester tester = null;

    @BeforeClass
    public static void beforeTest() throws Exception {
        tester = new JdbcDatabaseTester("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/todo_test", "root", "");
    }

    @Before
    public void setup() throws Exception {
        // 準備：初期データの設定
        //       テスト対象のオブジェクトの用意
        tester.setDataSet(new XlsDataSet(new File("src/test/initial_dataset.xls")));
        tester.onSetup();
    }

    @After
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    public void testGetList() throws Exception {

        SqlSessionFactory factory = SqlSessionManagerTest.getSqlSessionFactory();
        if (factory == null) {
            return;
        }
        List<Todo> list = new ArrayList<>();
        try (SqlSession session = factory.openSession()) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            list = mapper.getList();
        }

        //検証
        assertThat(list.size(), is(3));
    }

    @Test
    public void testAdd() throws Exception {

        SqlSessionFactory factory = SqlSessionManagerTest.getSqlSessionFactory();
        if (factory == null) {
            return;
        }
        try (SqlSession session = factory.openSession(true)) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            Todo todo = new Todo(0, "title4", "memo4");
            mapper.add(todo);
        }

        //①：テスト対象メソッド実行後の実際のテーブル内の状態を取得
        IDatabaseConnection conn = tester.getConnection();
        ITable actualTable = conn.createTable("todo");

        //②： 期待するデータセットをExcelファイルから取得
        IDataSet expectedDataSet = new XlsDataSet(new File("src/test/added_dataset.xls"));
        //③： 期待するテーブルの状態を取得
        ITable expectedTable = expectedDataSet.getTable("todo");
        //④： 期待するテーブルに定義されていないカラムを、実際のテーブルから除外
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

        //検証：テーブルの実際の状態と期待する状態を比較
        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @Test
    public void testDelete() throws Exception {

        SqlSessionFactory factory = SqlSessionManagerTest.getSqlSessionFactory();
        if (factory == null) {
            return;
        }
        try (SqlSession session = factory.openSession(true)) {
            TodoMapper mapper = session.getMapper(TodoMapper.class);
            Integer targetId = 0;
            List<Todo> list = mapper.getList();
            for (Todo todo : list) {
                if (todo.getTitle().equals("title3")) {
                    targetId = todo.getId();
                    break;
                }
            }
            mapper.delete(targetId);
        }

        //①：テスト対象メソッド実行後の実際のテーブル内の状態を取得
        IDatabaseConnection conn = tester.getConnection();
        ITable actualTable = conn.createTable("todo");

        //②： 期待するデータセットをExcelファイルから取得
        IDataSet expectedDataSet = new XlsDataSet(new File("src/test/deleted_dataset.xls"));
        //③： 期待するテーブルの状態を取得
        ITable expectedTable = expectedDataSet.getTable("todo");
        //④： 期待するテーブルに定義されていないカラムを、実際のテーブルから除外
        ITable filteredTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

        //検証：テーブルの実際の状態と期待する状態を比較
        Assertion.assertEquals(expectedTable, filteredTable);
    }
}
