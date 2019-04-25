package ru.siemens;

import org.json.JSONException;
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
public class TestViaSensor {
    private static final String QUERY_GET_ONE = "SELECT WIDTH, LONGITUDE, TEMPERATURE FROM TEMPERATURE_INDICATORS order by ID desc LIMIT 1";
    private Connection connection;
    private Statement statement;

    @Autowired
    private Application application;

    @Autowired
    private Controller controller;

    @Test(expected = ExceptionInvalidInput.class)
    public void whenPostMappingExceptionInvalidInputLongitude() {
        controller.add(new Metering("11.32405764", "22.132457Msd4", "27"));
    }

    @Test
    public void whentSentPostRequestViaMethodSensor() throws JSONException, SQLException, ClassNotFoundException {
        application.start();
        Metering metering = new Metering("11.22222222", "22.13245764", "33");
        String expected = metering.getWidth() + " " + metering.getLongitude() + " " + metering.getTemperature();
        new Sensor().sent(metering);
        assertThat(this.getOneElementFromDataBase().get(0), is(expected));
    }

    private List<String> getOneElementFromDataBase() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/weather", "postgres", "password123");
        this.statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERY_GET_ONE);
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(resultSet.getDouble("width")
                    + " " + resultSet.getDouble("longitude")
                    + " " + resultSet.getInt("temperature"));
        }
        return result;
    }
}
