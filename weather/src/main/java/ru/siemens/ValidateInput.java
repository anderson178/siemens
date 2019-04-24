package ru.siemens;


public class ValidateInput {

    public static boolean checkWL(int min, int max, String parameter) {
        String[] parsW = parameter.split("\\.");
        return parsW.length == 2 && isDigit(parsW[0]) && parsW[1].length() == 8 && isDigit(parsW[1])
                && isInterval(min, max, Integer.parseInt(parsW[0]));
    }



    public static boolean checkTemperature(int min, int max, String parameter) {
        return isDigit(parameter) && isInterval(min, max, Integer.parseInt(parameter));
    }

    private static boolean isDigit(String line) {
        try {
           Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

    private static boolean isInterval(int min, int max, int number) {
        return number >= min && number <= max;
    }


}
