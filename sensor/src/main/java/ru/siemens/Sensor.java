package ru.siemens;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Sensor {
    private static final String URL_EMPLOYES = "http://127.0.0.1:8080/map/put";

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String str = "03.02121=16.12121=26";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_EMPLOYES, str,String.class);
        System.out.println(responseEntity.getStatusCodeValue());
    }
}
