package settings;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class GraphicSettingsController extends AbstractController
{
    @FXML
    ChoiceBox<String> resolutionChoiceBox;

    ChoiceBox<String> themeChoiceBox;

    ChoiceBox<String> fontChoiceBox;

    public GraphicSettingsController()
    {
        themeChoiceBox.setItems(settingsModel.getAllThemes());
        resolutionChoiceBox.setItems(settingsModel.getAllResolutions());
        fontChoiceBox.setItems(settingsModel.getAllFonts());
    }

}
