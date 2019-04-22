package ru.siemens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/map")


public class Controller {
    private static final String QUERYGET = "SELECT WIDTH, LONGITUDE, TEMPERATURE,DATATIME FROM TEMPERATURE_INDICATORS order by DATATIME desc LIMIT 2";
    private SqlRowSet srs;

    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<String> getStr() {
        srs = jdbcTemplate.queryForRowSet(QUERYGET);
        List<String> rst = new ArrayList<>();
        while (srs.next()) {
            rst.add(srs.getString("WIDTH") + " " + srs.getString("LONGITUDE") + " " + srs.getInt("TEMPERATURE") + " " + srs.getDate("DATATIME"));
        }
        return rst;
    }

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    public void put(@RequestBody String value) {
        String[] parsStr = value.split("=");
        jdbcTemplate.update("insert into temperature_indicators (width, longitude, temperature) values (" + parsStr[0] + "," + parsStr[0] + "," + parsStr[2] + ")");
        System.out.println(value);
    }




    /*@PostMapping
    public void postStr(String str) {



    }*/
}
