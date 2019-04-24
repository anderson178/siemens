package ru.siemens;


public class ValidateInput {
    private final String str;
    private String[] date = new String[]{"", "", ""};


    public ValidateInput(String str) {
        this.str = str;
    }

    public boolean validate() {
        String[] parsStr = str.split("=");
        return parsStr.length == 3 && this.checkWL(0, 90, parsStr[0], 0)
                && this.checkWL(0, 180, parsStr[1], 1)
                && this.checkTemperature(-40, 50, parsStr[2], 2);
    }

    private boolean checkWL(int min, int max, String parameter, int indexDate) {
        boolean rst = false;
        String[] parsW = parameter.split("\\.");
        if (parsW.length == 2 && this.isDigit(parsW[0]) && parsW[1].length() == 8 && this.isDigit(parsW[1])) {
            int firstNumber = Integer.parseInt(parsW[0]);
            if (this.isInterval(min, max, firstNumber)) {
                this.date[indexDate] = parameter;
                rst = true;
            }
        }
        return rst;
    }

    private boolean checkTemperature(int min, int max, String parameter, int indexDate) {
        boolean rst = false;
        if (this.isDigit(parameter) && this.isInterval(min, max, Integer.parseInt(parameter))) {
            this.date[indexDate] = parameter;
            rst = true;
        }
        return rst;
    }

    private boolean isDigit(String line) {
        boolean str = true;
        for (char symbol : line.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                str = false;
                break;
            }
        }
        return str;
    }

    private boolean isInterval(int min, int max, int number) {
        return number >= min && number <= max;
    }

    public double getWidth() {
        return Double.parseDouble(this.date[0]);
    }

    public double getLongitude() {
        return Double.parseDouble(this.date[1]);
    }

    public int getTemperature() {
        return Integer.parseInt(this.date[2]);
    }


}
