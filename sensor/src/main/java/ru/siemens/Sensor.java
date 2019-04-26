package ru.siemens;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 * <p>
 * A class that sends a set of parameters to a data processing service.
 */

public class Sensor {
    private static final String URL = "http://127.0.0.1:8080/map/add";

    /**
     * Method a that sends a set of parameters to a data processing service - weather
     *
     * @param metering
     */
    public void toSend(Metering metering) {
        ResponseEntity<Metering> responseEntity = new RestTemplate().postForEntity(
                URL, metering, Metering.class);
        System.out.println(responseEntity.getStatusCode());
    }

    public static void main(String[] args) {
        new Sensor().toSend(new Metering("41.32405764", "01.13245764", "39"));
    }
}
