package ru.siemens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import ru.siemens.exception.ExceptionInvalidInput;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(value = "/map")
public class Controller {
    private static final String QUERY_GET_TEN = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by DATATIME desc LIMIT 10";
    private static final int MINW = 0;
    private static final int MAXW = 90;
    private static final int MINL = 0;
    private static final int MAXL = 180;
    private static final int MINT = -40;
    private static final int MAXT = 40;
    private SqlRowSet srs;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//produces =
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody Metering metering) {
        String width = metering.getWidth();
        String longitude = metering.getLongitude();
        String temperature = metering.getTemperature();
        if (ValidateInput.checkWL(MINW, MAXW, width) && ValidateInput.checkWL(MINL, MAXL, longitude)
                && ValidateInput.checkTemperature(MINT, MAXT, temperature)) {
            String values = "'" + width + "','" + longitude + "','" + temperature + "','"
                    + this.getCurrenntDataTime() + "'";
            jdbcTemplate.update("insert into temperature_indicators (width, longitude, temperature, datatime)"
                    + "values (" + values + ")");
        } else {
            throw new ExceptionInvalidInput("");
        }

    }

    private String getCurrenntDataTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}
