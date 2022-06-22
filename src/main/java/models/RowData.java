package models;

/**
 * The data for a single row
 *
 * @author Tamim
 */
public class RowData {
    /**
     * The date this row was recorded at
     */
    public final Date date;
    /**
     * The value of the data
     */
    public final double value;

    /**
     * Creates a {@link RowData} object with a certain data and value
     *
     * @param date  Value's date
     * @param value The value recorded
     */
    public RowData(String date, double value) {
        this.date = new Date(date);
        this.value = value;
    }
}