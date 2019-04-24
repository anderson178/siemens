package ru.siemens;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Sensor {
    private static final String URL_EMPLOYES = "http://127.0.0.1:8080/map/add";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String str = "66.12302121=36.3245764=3";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_EMPLOYES, str,String.class);
        System.out.println(responseEntity.getStatusCode());
    }
}
