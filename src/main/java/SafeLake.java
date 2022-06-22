import framework.ScenicApplication;
import framework.ScenicLoader;
import framework.ScenicStage;

import java.io.IOException;

/**
 * Entrypoint class
 *
 * <p>SafeLake is an app made in Swing MVC to showcase lake pollution data</p>
 *
 * @author Tamim, Maya
 */
public class SafeLake extends ScenicApplication {
    /**
     * Main function
     *
     * <p>Creates the needed MVC elements and runs them as needed</p>
     *
     * @param args The run arguments, are unused
     */
    public static void run(String[] args) {
        launch(args);
    }


    @Override
    public void scenicStart(ScenicStage stage) {
        try {
            ScenicLoader.setFxmlFilePath("/fxml/");
            String[] pages = {"Home", "Map", "Sources", "About"};
            for (String page : pages) {
                ScenicLoader scenicLoader = new ScenicLoader(page);
                stage.addScene(scenicLoader);
            }
            stage.setScene("Home");
            stage.setTitle("SafeLake");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}