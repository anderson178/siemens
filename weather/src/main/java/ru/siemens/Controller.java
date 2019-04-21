package ru.siemens;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/map")
public class Controller {

    @GetMapping
    public List<String> getStr() {
       //JdbcTemplate jdbc = new JdbcTemplate();
       // List<String> list = jdbc.queryForList("SELECT WIDTH FROM TEMPERATURE_INDICATORS", String.class);
        return new JdbcTemplate().queryForList("SELECT WIDTH FROM TEMPERATURE_INDICATORS", String.class);
       //return "wwwwwwwww";
    }

    /*@PostMapping
    public void postStr(String str) {



    }*/
}
