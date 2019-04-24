package ru.siemens;


public class ValidateInput {

    public static boolean checkWL(int min, int max, String parameter) {
        boolean rst = true;
        String[] parsW = parameter.split("\\.");
        return parsW.length == 2 && isDigit(parsW[0]) && parsW[1].length() == 8 && isDigit(parsW[1])
                && isInterval(min, max, Integer.parseInt(parsW[0]));
    }

    public static boolean checkTemperature(int min, int max, String parameter) {
        return isDigit(parameter) && isInterval(min, max, Integer.parseInt(parameter));
    }

    private static boolean isDigit(String line) {
        boolean str = true;
        for (char symbol : line.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                str = false;
                break;
            }
        }
        return str;
    }

    private static boolean isInterval(int min, int max, int number) {
        return number >= min && number <= max;
    }


}
