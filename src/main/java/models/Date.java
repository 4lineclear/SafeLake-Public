package models;

/**
 * A class to store the date as three integers
 * Takes a string formatted as YYYY/MM/DD
 **/
public class Date {
    final int month, day, year;

    /**
     * Create a Date object with a specified date
     *
     * @param date A date formatted as YYYY/MM/DD
     **/
    public Date(String date) {
        int[] dates = parseYYYYMMDD(date);
        month = dates[0];
        day = dates[1];
        year = dates[2];
    }

    /**
     * Takes date in string format and returns as array of three integers
     *
     * @param date is a date containing year, month, and day in string format
     * @return is an array of three integers representing month, day, year
     */
    private int[] parseYYYYMMDD(String date) {
        String[] strDates = date.split("/");
        int[] intDates = new int[3];
        for (int i = 0; i < 3; i++)
            intDates[i] = Integer.parseInt(strDates[i]);

        return intDates;
    }
}
