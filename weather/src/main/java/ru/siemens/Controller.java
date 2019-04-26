package ru.siemens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import ru.siemens.exception.ExceptionInvalidInput;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 * <p>
 * Processing class get and post requests
 */


@RestController
@RequestMapping(value = "/map")
public class Controller {
    private static final String QUERY_GET_TEN = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by ID desc LIMIT 10";
    private static final int MIN_W = 0;
    private static final int MAX_W = 90;
    private static final int MIN_L = 0;
    private static final int MAX_L = 180;
    private static final int MIN_T = -40;
    private static final int MAX_T = 40;
    private SqlRowSet srs;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Processing method get request
     *
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getStr() {
        srs = jdbcTemplate.queryForRowSet(QUERY_GET_TEN);
        List<String> rst = new ArrayList<>();
        while (srs.next()) {
            rst.add(srs.getString("WIDTH") + " " + srs.getString("LONGITUDE")
                    + " " + srs.getInt("TEMPERATURE") + " " + srs.getDate("DATATIME")
                    + " " + srs.getTime("DATATIME"));
        }
        return rst;
    }

    /**
     * Processing method post requestю.
     * Accepts object class metering. Retrieves a set of marameters.
     * Checks for validates input parameters.
     * If everything is in order, it will write to the database, otherwise it will throw an
     * exception and send the status BED_REQUEST
     *
     * @param metering - object of class Metering
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Metering metering) {
        String width = metering.getWidth();
        String longitude = metering.getLongitude();
        String temperature = metering.getTemperature();
        if (ValidateInput.checkWL(MIN_W, MAX_W, width) && ValidateInput.checkWL(MIN_L, MAX_L, longitude)
                && ValidateInput.checkTemperature(MIN_T, MAX_T, temperature)) {
            String values = "'" + width + "','" + longitude + "','" + temperature + "','"
                    + this.getCurrenntDataTime() + "'";
            jdbcTemplate.update("insert into temperature_indicators (width, longitude, temperature, datatime)"
                    + "values (" + values + ")");
        } else {
            throw new ExceptionInvalidInput("invalid data input");
        }

    }

    /**
     * Method that forms the current date and time
     *
     * @return
     */
    private String getCurrenntDataTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}
