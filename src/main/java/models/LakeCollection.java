package models;

import java.util.HashMap;
import java.util.List;

/**
 * Collection of Lake Pollution data
 *
 * <p>Contains collections for the different lakes that data will be stored for</p>
 *
 * @author Tamim
 */
public class LakeCollection extends HashMap<Lake, LakeData> {
    /**
     * Only constructor for the class
     * <p>Creates a {@link LakeData} object for each of the lakes found in {@link Lake}</p>
     */
    public LakeCollection() {
        for (Lake lake : Lake.values()) {
            this.put(lake, new LakeData(lake));
        }
    }

    /**
     * Adds a row and sorts it into the correct object
     *
     * @param row The row to add
     * @see LakeData#add(String[])
     */
    public void add(String[] row) {
        this.get(Lake.getLake(row[0])).add(row);

    }
}
