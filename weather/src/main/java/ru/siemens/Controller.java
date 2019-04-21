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
        return new ArrayList<>(Arrays.asList("1223", "hi", "bye"));
    }

    @PostMapping
    public void postStr(String str) {
        JdbcTemplate jdbc = new JdbcTemplate();


    }
}
