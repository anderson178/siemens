package ru.siemens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 */

/**
 * Class to run the application
 */
@SpringBootApplication
public class Application {
    public void start() {
        SpringApplication.run(Application.class);
    }

    public static void main(String[] args) {
        new Application().start();
    }
}
