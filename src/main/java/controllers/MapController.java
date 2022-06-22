package controllers;

import framework.ScenicController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Lake;
import models.LakeCollection;
import models.Model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Controls the Map page
 **/
public class MapController extends ScenicController {
    /*
     * ImageView for each flag,
     * every one is for a different lake
     * (Specified within its name)
     * */
    public ImageView erie;
    public ImageView huron;
    public ImageView superior;
    public ImageView ontario;

    /**
     * The saved data, is provided by {@link Model}
     **/
    LakeCollection data;

    /**
     * A map to get the {@link Lake} each {@link ImageView} points to,
     * initialized within {@link MapController#initialize()}
     */
    Map<ImageView, Lake> lakeMap;

    public void backClicked(MouseEvent mouseEvent) {
        setScene("Home");
    }

    /**
     * Runs at the initialization of the class,
     * which {@link FXMLLoader} handles
     * <br>
     * Creates a {@link Model} object and
     * uses it to read data from the sheet
     */
    @FXML
    public void initialize() {
        try {
            this.data = new Model().readData("/data/Lake Data.csv");

        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageView[] imageViews = {erie, huron, superior, ontario};
        Lake[] lakes = Lake.values();
        lakeMap = new HashMap<>();

        for (int i = 0; i < imageViews.length; i++) {
            lakeMap.put(imageViews[i], lakes[i]);
        }
    }

    /**
     * Run when a flag is clicked
     * <br>
     * All flags use the same method,
     * so also discerns the particular flag which was clicked
     *
     * @param mouseEvent Given by JavaFX, used to where the click came from
     * @throws IOException The FXML file was not loaded properly
     */
    public void flagClicked(MouseEvent mouseEvent) throws IOException {
        //Create a new window to show the data
        Stage popup = new Stage();
        FXMLLoader popupLoader = new FXMLLoader(Objects.requireNonNull(this.getClass().getResource("/fxml/Popup.fxml")));
        Scene scene = new Scene(popupLoader.load());
        PopupController popupController = popupLoader.getController(); //Getting the instance of the controller to inject the data

        var lake = lakeMap.get((ImageView) mouseEvent.getSource()); //The lake that the source of the mouse event points to

        popupController.setData(data.get(lake)); //Injects the data into the Controller, which the injects the data into the chart

        //Sets up the window,
        //Making its unmovable, unclose-able(without the button we provide), always on top,
        //and making it appear on the button
        popup.setScene(scene);
        popup.setX(mouseEvent.getScreenX() - 300);
        popup.setY(mouseEvent.getScreenY() - 200);
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setAlwaysOnTop(true);
        popup.setTitle(lake.name() + " " + "Charts");
        popup.showAndWait();
    }
}
