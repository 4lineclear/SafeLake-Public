package controllers;

import framework.ScenicController;
import javafx.scene.input.MouseEvent;

public class HomeController extends ScenicController {

    public void mapClicked() {
        setScene("Map");
    }

    public void sourcesClicked(MouseEvent mouseEvent) {
        setScene("Sources");
    }


    public void aboutClicked(MouseEvent mouseEvent) {
        setScene("About");
    }
}