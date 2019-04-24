package ru.siemens;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;


public class Sensor {
    private static final String URL_EMPLOYES = "http://127.0.0.1:8080/map/add";


    public static JSONObject toJason(String widt, String longitude, String temperature) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("width", widt);
        jsonObject.put("longitude", longitude);
        jsonObject.put("temperature", temperature);
        return jsonObject;
    }


    public static void main(String[] args) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObject = toJason("46.32405764", "36.13245764", "21");



        String str = "66.12302121=36.3245764=3";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_EMPLOYES, jsonObject.toString(), String.class);
        System.out.println(responseEntity.getStatusCode());
    }
}
