package ru.siemens;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.siemens.exception.ExceptionInvalidInput;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@TestPropertySource(locations = "classpath:application.properties")
public class ControllerTest {
    private static final String QUERY_GET_TEN = "SELECT WIDTH, LONGITUDE, TEMPERATURE, DATATIME FROM TEMPERATURE_INDICATORS order by ID desc LIMIT 10";
    private static final String QUERY_GET_ONE = "SELECT WIDTH, LONGITUDE, TEMPERATURE FROM TEMPERATURE_INDICATORS order by ID desc LIMIT 1";

    @Autowired
    private Controller controller;

    @Test
    public void whenGetMapping() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Statement statement = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/weather", "postgres", "password123");
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_GET_TEN);
        List<String> expected = new ArrayList<>();
        while (resultSet.next()) {
            expected.add(resultSet.getDouble("width") + " " + resultSet.getDouble("longitude")
                    + " " + resultSet.getInt("temperature")
                    + " " + resultSet.getDate("datatime") + " " + resultSet.getTime("datatime"));
        }

        assertThat(controller.getStr(), is(expected));
    }

    @Test
    public void whenPostMapping() throws SQLException, ClassNotFoundException {
        Metering metering = new Metering("11.32405764", "22.13245764", "33");
        String expected = metering.getWidth() + " " + metering.getLongitude() + " " + metering.getTemperature();
        controller.add(metering);
        Connection connection = null;
        Statement statement = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/weather", "postgres", "password123");
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_GET_ONE);
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getDouble("width")
                    + " " + resultSet.getDouble("longitude")
                    + " " + resultSet.getInt("temperature"));
        }
        assertThat(result.get(0), is(expected));
    }



    @Test(expected = ExceptionInvalidInput.class)
    public void whenPostMappingExceptionInvalidInputTemperature()  {
        controller.add(new Metering("11.32405764", "22.13245764", "77"));
    }

    @Test(expected = ExceptionInvalidInput.class)
    public void whenPostMappingExceptionInvalidInputWidth()  {
        controller.add(new Metering("11.3240576P", "22.13245764", "33"));
    }

    @Test(expected = ExceptionInvalidInput.class)
    public void whenPostMappingExceptionInvalidInputLongitude()  {
        controller.add(new Metering("11.32405764", "22.132457Msd4", "27"));
    }



}
