package org.example;
import org.dbunit.*;
import org.dbunit.database.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.dbunit.operation.DatabaseOperation;
import org.junit.*;

import java.sql.*;

import static org.junit.Assert.assertEquals;

public class TestDatabase {
    static final String Dataset_File = "dataset.xml";
    private static final String TableName = "task1_table";
    private Connection connection;

    @Before
    public void setUp() throws Exception{
        connection = DriverManager.getConnection(Database.Database_url, Database.Login, Database.Pass);
        IDatabaseConnection dbConnection = new DatabaseConnection(connection);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream(Dataset_File));
        DatabaseOperation.CLEAN_INSERT.execute(dbConnection, dataSet);
    }

    @Test
    public void testDatabaseQuery() throws Exception {

        Statement statement = connection.createStatement();
        String query = "SELECT COUNT(*) FROM " + TableName;
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();

        int count = resultSet.getInt(1);
        int expRes = 3;
        assertEquals(expRes, count);

        resultSet.close();
        statement.close();
    }

    @After
    public void tearDown() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
