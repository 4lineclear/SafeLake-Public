package controllers;

import framework.ScenicController;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutController extends ScenicController {
    String[] hyperlinkLabel = {"Nitrate in Drinking Water", "Fertilizers and nitrate pollution of surface and ground water: an increasingly pervasive global problem", "What can phosphorus tell us about the condition of water?", "Aquatic Life Criteria - Ammonia", "Rapid effects of diverse toxic water pollutants on chlorophyll a fluorescence: variable responses among freshwater microalgae", "Silicon (Si) and water", "DISSOLVED ORGANIC CARBON (DOC)", "Chloride", "Turbidity"};
    String[] links = {"https://www.health.state.mn.us/communities/environment/water/contaminants/nitrate.html", "https://link.springer.com/article/10.1007/s42452-021-04521-8#:~:text=Nitrogen%20(N)%20in%20the%20form,water%20unfit%20for%20drinking%20purposes.", "https://www.epa.gov/national-aquatic-resource-surveys/indicators-phosphorus#:~:text=Too%20much%20phosphorus%20can%20cause,to%20human%20and%20animal%20health.", "https://www.epa.gov/wqc/aquatic-life-criteria-ammonia", "https://pubmed.ncbi.nlm.nih.gov/22406285/", "https://www.lenntech.com/periodic/water/silicon/silicon-and-water.htm#:~:text=Large%20amounts%20of%20silicon%20can,generally%20contain%204%20ppm%20silicon.", "https://realtechwater.com/parameters/dissolved-organic-carbon/#:~:text=For%20drinking%20water%2C%20Dissolved%20Organic,for%20removal%20prior%20to%20disinfection.", "https://www.canada.ca/en/health-canada/services/publications/healthy-living/guidelines-canadian-drinking-water-quality-guideline-technical-document-chloride.html", "https://www.canada.ca/en/health-canada/services/publications/healthy-living/guidelines-canadian-drinking-water-quality-turbidity/page-5-guidelines-canadian-drinking-water-quality-turbidity.html"};

    public void backClicked(MouseEvent mouseEvent) {
        setScene("Home");
    }

    public void pollutantLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        String linkLabel = ((Hyperlink) actionEvent.getSource()).getText();
        for (int i = 0; i < links.length; i++) {
            if (linkLabel.equals(hyperlinkLabel[i])) {
                Desktop.getDesktop().browse(new URI(links[i]));
                return;
            }
        }
    }
}
