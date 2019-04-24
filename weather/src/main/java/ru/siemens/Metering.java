package ru.siemens;

public class Metering {
    private String width;
    private String longitude;
    private String temperature;

    public Metering(String width, String longitude, String temperature) {
        this.width = width;
        this.longitude = longitude;
        this.temperature = temperature;
    }

    public String getWidth() {
        return width;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getTemperature() {
        return temperature;
    }
}

