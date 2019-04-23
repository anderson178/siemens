package ru.siemens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/map")


public class Controller {
    private static final String QUERYGET = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by DATATIME desc LIMIT 5";
    private SqlRowSet srs;

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getStr() {
        srs = jdbcTemplate.queryForRowSet(QUERYGET);
        List<String> rst = new ArrayList<>();
        while (srs.next()) {
            rst.add(srs.getString("WIDTH") + " " + srs.getString("LONGITUDE") + " " + srs.getInt("TEMPERATURE") + " " + srs.getDate("DATATIME") + " " + srs.getTime("DATATIME"));
        }
        return rst;
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public void put(@RequestBody String value) {
        String[] parsStr = value.split("=");
        String values = "'" + parsStr[0] + "','" +  parsStr[1] + "','" + parsStr[2] + "','" + this.getCurrenntDataTime() + "'";
        jdbcTemplate.update("insert into temperature_indicators (width, longitude, temperature, datatime) values ("+values+")");
        System.out.println(value);
    }

    private String getCurrenntDataTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }




    /*@PostMapping
    public void postStr(String str) {



    }*/
}
