package uk.ac.warwick.dcs.chess;
/**
 * This class contains utility functions that are useful to the application.
 */
public class Utilities {

    /**
     * This method takes a character and converts it into an integer between 1-8, using the offset in the chracter table.
     * @param string The string value to be converted.
     * @return
     */
    public static int letterToIndex(String string) {
        char c = string.substring(0,1).charAt(0);
        int value = ((int)c) - 97;
        return value;
    }

    public static int numberToIndex(String string) {
        int value = Integer.parseInt(string);

        //Need to reverse, as we start counting from bottom of board but it is the maximum position.
        int index = 8-value;
        return index;

    }
}
