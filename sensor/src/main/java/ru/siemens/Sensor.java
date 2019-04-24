package ru.siemens;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Sensor {
    private static final String URL = "http://127.0.0.1:8080/map/add";


    private JSONObject toJason(String widt, String longitude, String temperature) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("width", widt);
        jsonObject.put("longitude", longitude);
        jsonObject.put("temperature", temperature);
        return jsonObject;
    }

    public void sent(String width, String longitude, String temperature) throws JSONException {
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(
                URL, toJason(width, longitude, temperature).toString(), String.class);
        System.out.println(responseEntity.getStatusCode());
    }

    public static void main(String[] args) throws JSONException {
        new Sensor().sent("46.32405764", "36.13245764", "39");
    }
}
