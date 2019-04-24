package ru.siemens;


import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private static final String QUERYGET = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by DATATIME desc LIMIT 5";
    private static final int MINW = 0;
    private static final int MAXW = 90;
    private static final int MINL = 0;
    private static final int MAXL = 180;
    private static final int MINT = -40;
    private static final int MAXT = 40;
    private SqlRowSet srs;

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getStr() {
        srs = jdbcTemplate.queryForRowSet(QUERYGET);
        List<String> rst = new ArrayList<>();
        while (srs.next()) {
            rst.add(srs.getString("WIDTH") + " " + srs.getString("LONGITUDE")
                    + " " + srs.getInt("TEMPERATURE") + " " + srs.getDate("DATATIME")
                    + " " + srs.getTime("DATATIME"));
        }
        return rst;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody String str) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(str);
        String width = jsonObject.get("width").toString();
        String longitude = jsonObject.get("longitude").toString();
        String temperature = jsonObject.get("temperature").toString();
        if (ValidateInput.checkWL(MINW, MAXW, width) && ValidateInput.checkWL(MINL, MAXL, longitude)
                && ValidateInput.checkTemperature(MINT, MAXT, temperature)) {
            String values = "'" + width + "','" + longitude + "','" + temperature + "','"
                    + this.getCurrenntDataTime() + "'";
            jdbcTemplate.update("insert into temperature_indicators (width, longitude, temperature, datatime)"
                    + "values (" + values + ")");
        } else {
            throw new ExceptionInvalidInput();
        }

    }

    private String getCurrenntDataTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }


}
