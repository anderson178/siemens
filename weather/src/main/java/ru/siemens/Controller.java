package ru.siemens;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(value = "/map")


public class Controller {


    @Qualifier("jdbcTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;



    @GetMapping
    public List<String> getStr() {
        return jdbcTemplate.queryForList("SELECT WIDTH FROM TEMPERATURE_INDICATORS", String.class);
    }


    /*@PostMapping
    public void postStr(String str) {



    }*/
}
