package ru.siemens;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Sensor {
    private static final String URL = "http://127.0.0.1:8080/map/add";


    public void sent(Metering metering) throws JSONException {
        ResponseEntity<Metering> responseEntity = new RestTemplate().postForEntity(
                URL, metering, Metering.class);
        System.out.println(responseEntity.getStatusCode());
    }

    public static void main(String[] args) throws JSONException {
        new Sensor().sent(new Metering("41.32405764", "01.13245764", "39"));
    }
}
