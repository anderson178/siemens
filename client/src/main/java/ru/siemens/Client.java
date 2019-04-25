package ru.siemens;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private static final String URL_WEATHER = "http://127.0.0.1:8080/map/get";

    public List<String> get() {
        RestTemplate restTemplate = new RestTemplate();
        String[] result = restTemplate.getForObject(URL_WEATHER, String[].class);
        return result != null ? Arrays.stream(result).collect(Collectors.toList()) : new ArrayList<>();
    }

    public static void main(String[] args) {
        new Client().get();
    }
}
