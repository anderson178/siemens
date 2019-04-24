package ru.siemens;

import org.springframework.web.client.RestTemplate;

public class Client {
    private static final String URL_WEATHER = "http://127.0.0.1:8080/map/get";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String[] rst = restTemplate.getForObject(URL_WEATHER, String[].class);
        if (rst != null) {
            for (String data : rst) {
                System.out.println(data);
            }
        }
    }
}
