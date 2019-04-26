package ru.siemens;

/**
 * @author Денис Мироненко
 * @version $Id$
 * @since 26.04.2019
 * <p>
 * Class that validates input parameters
 */

public class ValidateInput {

    /**
     * Method that checks the width and longitude of the specified parameters
     *
     * @param min       - minimum value
     * @param max       - mximum value
     * @param parameter - input parametrs
     * @return
     */
    public static boolean checkWL(int min, int max, String parameter) {
        String[] parsW = parameter.split("\\.");
        return parsW.length == 2 && isDigit(parsW[0]) && parsW[1].length() == 8 && isDigit(parsW[1])
                && isInterval(min, max, Integer.parseInt(parsW[0]));
    }

    /**
     * Method that checks the temperature of the specified parameters
     *
     * @param min       - minimum value
     * @param max       - mximum value
     * @param parameter - input parametrs
     * @return
     */
    public static boolean checkTemperature(int min, int max, String parameter) {
        return isDigit(parameter) && isInterval(min, max, Integer.parseInt(parameter));
    }

    /**
     * The method checks if the line is a number
     *
     * @param line
     * @return
     */
    private static boolean isDigit(String line) {
        try {
            Integer.parseInt(line);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

    /**
     * The method checks if the number is in the specified interval.
     *
     * @param min
     * @param max
     * @param number
     * @return
     */
    private static boolean isInterval(int min, int max, int number) {
        return number >= min && number <= max;
    }

}
