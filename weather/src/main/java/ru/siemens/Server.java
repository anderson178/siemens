package ru.siemens;


import org.springframework.boot.autoconfigure.SpringBootApplication;


public class Server {

    public void start(String line) {
        String[] parsLine = parsLine(line);
        boolean rat = new ValidateInput(line).validate();
    }


    private String[] parsLine(String line) {
        String[] result = line.split("=");
        if (result.length != 3) {
            System.out.println("Error inpud date");
        }
        return result;

    }

    public static void main(String[] args) {

    }

}
