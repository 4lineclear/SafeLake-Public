package models;

import java.util.HashMap;

/**
 * The collection for data of a single lake
 * <p>
 * Extends a {@link HashMap} with {@link String} as keys and {@link RowCollection} as values
 * </p>
 * <p>
 * Also stores which lake its storing data for
 * </p>
 *
 * @author Tamim
 */
public class LakeData extends HashMap<String, RowCollection> {
    /**
     * The lake that the data is being stored for
     * <p>There should only me one {@link LakeData} object for each {@link Lake}</p>
     */
    public final Lake lake;

    /**
     * Creates a {@link LakeData} object for a specified {@link Lake}
     *
     * @param lake The lake to specify the object for
     */
    public LakeData(Lake lake) {
        this.lake = lake;

    }

    /**
     * Adds a row of data and filters it appropriately
     *
     * @param row The row of data to hold
     **/
    public void add(String[] row) {
        if (!this.containsKey(row[2]))
            this.put(row[2], new RowCollection(row[2], row[4]));
        this.get(row[2]).add(new RowData(row[1], Double.parseDouble(row[3])));
    }
}