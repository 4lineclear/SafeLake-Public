package models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Lake pollution data holder/repo
 *
 * <p>Model of the MVC pattern, holds, manipulates and returns Lake Pollution data as needed</p>
 *
 * @author Maya, Tamim
 */
public class Model {

    /**
     * Storage for all the lake data
     *
     * <p>Only a single instance of the data should be made</p>
     */
    LakeCollection data;

    /**
     * Constructor for the {@link Model} class
     */
    public Model() {
        this.data = new LakeCollection();
    }

    /**
     * Reads data from a specified spreadsheet, with a specified range,
     * then saves that data to {@link Model#data}
     *
     * @param fileName The name of the file to read
     * @return A {@link LakeCollection} object filled with the read data
     */
    public LakeCollection readData(String fileName) throws java.io.IOException {
        try (InputStream inputStream = this.getClass().getResourceAsStream(fileName)) {
            InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(inputStream));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null)
                this.data.add(line.split(","));

        }
        return data;
    }

    /**
     * @return The data saved by {@link Model#readData(String)}
     */
    public LakeCollection getData() {
        return this.data;
    }


}