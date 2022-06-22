package controllers;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import models.LakeData;
import models.RowCollection;

import java.util.Map;

/**
 * Controls the popup that is made by {@link MapController}
 * The popup contains a chart for a specified {@link models.Lake},
 * buttons to switch between the different pollutants and a button to close the popup
 * <br>
 * The popup is unmovable and appears on the flag that is clicked
 **/
public class PopupController {

    /**
     * The current pollutant being shown,
     * is used in conjunction with {@link PopupController#pollutants}
     **/
    private static int pollutantIndex = 0;
    /***
     * The that contains everything,
     * Charts are inputted into it
     */
    public GridPane mainPanel;
    /**
     * All the data for the particular lake
     * which is being presented
     **/
    private LakeData data;
    /**
     * An array of all lake names
     * <br>
     * Is used so the data's {@link Map#keySet()} is not constantly called
     */
    private String[] pollutants;

    /**
     * Injects the data to present,
     * is called when the popup is made
     * <br>
     * Then adds a chart to the {@link PopupController#mainPanel}
     *
     * @param data The data to present
     * @see MapController#flagClicked(MouseEvent)
     **/
    public void setData(LakeData data) {
        this.data = data;
        this.pollutants = this.data.keySet().toArray(new String[0]);
        mainPanel.add(createChart(), 1, 1);
    }

    public void exitClicked(MouseEvent mouseEvent) {
        //Closes the window, huge code smell but works
        ((Node) mouseEvent.getSource()).getScene().getWindow().hide();
    }


    /**
     * Run when left pointing arrow is clicked
     * <br>
     * Switches the current chart to the next one,
     * if on the last pollutant, goes to the first
     *
     * @param mouseEvent Provided by JavaFX, not used for anything
     * @see PopupController#back(MouseEvent)
     **/
    public void forward(MouseEvent mouseEvent) {
        pollutantIndex++;
        if (pollutantIndex + 1 > pollutants.length)
            pollutantIndex = 0;
        setChartData();
    }

    /**
     * Run when right pointing arrow is clicked
     * <br>
     * Switches the current chart to the previous one,
     * if on the first pollutant, goes to the last
     *
     * @param mouseEvent Provided by JavaFX, not used for anything
     * @see PopupController#forward(MouseEvent)
     **/
    public void back(MouseEvent mouseEvent) {
        pollutantIndex--;
        if (pollutantIndex < 0)
            pollutantIndex = pollutants.length - 1;
        setChartData();
    }

    /**
     * Removes current chart, and adds a new one
     *
     * @see PopupController#createChart()
     **/
    private void setChartData() {
        mainPanel.getChildren().remove(mainPanel.getChildren().size() - 1);
        mainPanel.add(createChart(), 1, 1);
    }

    /**
     * Creates a new chart based on the current pollutant
     * Inputs the correct axes, labels and data
     *
     * @see PopupController#data
     * @see PopupController#pollutants
     * @see PopupController#pollutantIndex
     **/
    private LineChart<Number, Number> createChart() {
        //ALL HAIL TURBIDITY

        //Get the data for the new chart
        RowCollection rows = data.get(pollutants[pollutantIndex]);

        //Create the new axes
        NumberAxis xAxis = new NumberAxis(1994, 2020, 1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(rows.unit);
        xAxis.setLabel("Year");

        xAxis.setTickLabelFormatter(new StringConverter<>() {

            @Override
            public String toString(Number num) {
                return String.valueOf(num.intValue());
            }

            @Override
            public Number fromString(String string) {
                return Integer.valueOf(string);
            }
        });
        //Create new chart based on new axes
        LineChart<Number, Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setTitle(data.lake.name() + " - " + rows.parameter);
        chart.setLegendVisible(false);

        //Input new data
        var xyChart = new XYChart.Series<Number, Number>();
        for (RowCollection.YearlyMean mean : rows.getYearlyMeans())
            xyChart.getData().add(new XYChart.Data<>(mean.year, mean.getMean()));
        chart.getData().add(xyChart);

        //Return chart outfitted with the proper axes, labels and data
        return chart;
    }
}

