package controllers;

import framework.ScenicController;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class SourcesController extends ScenicController {
    String[] hyperlinkLabel = {"Ontario.ca Data Catalogue", "Java Quickstart API", "IntelliJ IDLE", "Gradle Build Tool"};
    String[] links = {"https://data.ontario.ca/dataset/lake-water-quality-at-drinking-water-intakes", "https://developers.google.com/sheets/api/quickstart/java", "https://www.jetbrains.com/idea/", "https://gradle.org/"};

    public void backClicked(MouseEvent mouseEvent) {
        setScene("Home");
    }

    public void openLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        String linkLabel = ((Hyperlink) actionEvent.getSource()).getText();
        for (int i = 0; i < links.length; i++) {
            if (linkLabel.equals(hyperlinkLabel[i])) {
                Desktop.getDesktop().browse(new URI(links[i]));
                return;
            }
        }
    }
}
