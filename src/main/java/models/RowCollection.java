package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The data for a collection for rows
 *
 * @author Tamim
 */
public class RowCollection extends ArrayList<RowData> {
    /**
     * The unit the data is stored in
     */
    public final String parameter, unit;
    private final Map<Integer, YearlyMean> yearlyMeans;

    /**
     * Creates a {@link RowCollection} object with a certain unit
     *
     * @param unit The unit in which data is stored in
     */
    public RowCollection(String parameter, String unit) {
        this.unit = unit;
        this.parameter = parameter;
        this.yearlyMeans = new HashMap<>();

    }

    @Override
    public boolean add(RowData data) {
        if (!yearlyMeans.containsKey(data.date.year))
            yearlyMeans.put(data.date.year, new YearlyMean(data.date.year));
        yearlyMeans.get(data.date.year).add(data.value);

        return super.add(data);
    }

    public Collection<YearlyMean> getYearlyMeans() {
        return yearlyMeans.values();
    }

    public
            /**
             * The mean for the values of a single year
             * */
    class YearlyMean {
        /**
         * The year data is being saved for
         **/
        public final int year;
        /**
         * The total for the specified year
         **/
        private double total = 0;
        /**
         * The number of values data is being stored for
         **/
        private int totalValues = 0;

        /**
         * Create a container for the mean of a specified year
         *
         * @param year The year data is being saved for
         **/

        public YearlyMean(int year) {
            this.year = year;
        }

        /**
         * Add a value to the total
         *
         * @param value A value for the specified year
         **/
        public void add(double value) {
            totalValues++;
            total += value;
        }

        /**
         * @return The mean for the year
         */
        public double getMean() {
            return total / totalValues;
        }

    }
}
