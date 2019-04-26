package ru.siemens;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 * <p>
 * The class checks the application using the Client class.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TestViaClient {
    private static final String QUERY_GET_TEN = "SELECT WIDTH, LONGITUDE, TEMPERATURE, DATATIME FROM TEMPERATURE_INDICATORS order by ID desc LIMIT 10";
    private Connection connection;
    private Statement statement;

    @Autowired
    private Application application;

    @Test
    public void whenClientGetRequestViaMethodClient() throws SQLException, ClassNotFoundException {
        application.start();
        assertThat(new Client().get(), is(this.getTenElementsFromDataBase()));
    }

    private List<String> getTenElementsFromDataBase() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/weather", "postgres", "password123");
        this.statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_GET_TEN);
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getDouble("width") + " " + resultSet.getDouble("longitude")
                    + " " + resultSet.getInt("temperature")
                    + " " + resultSet.getDate("datatime") + " " + resultSet.getTime("datatime"));
        }
        return result;
    }
}
