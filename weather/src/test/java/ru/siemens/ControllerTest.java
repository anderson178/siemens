package ru.siemens;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
@TestPropertySource(locations="classpath:application.properties")
public class ControllerTest {
    private static final String QUERYGET = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by DATATIME desc LIMIT 5";
    @Autowired
    private Controller controller;

    @Test
    public void whenGetMapping() throws Exception {
        Connection connection = null;
        Statement statement = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/weather", "postgres", "password123");
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(QUERYGET);
        List<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(resultSet.getDouble("width") + " " + resultSet.getDouble("longitude")
                    + " " + resultSet.getInt("temperature")
                    + " " + resultSet.getDate("datatime") + " " + resultSet.getTime("datatime"));


        }
        assertThat(controller.getStr(), is(list));


    }


}
