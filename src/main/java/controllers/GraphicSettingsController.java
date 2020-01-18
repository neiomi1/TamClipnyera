package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class GraphicSettingsController extends AbstractController
{
    @FXML
    ChoiceBox<String> resolutionChoiceBox;

    @FXML
    ChoiceBox<String> themeChoiceBox;

    @FXML
    ChoiceBox<String> fontChoiceBox;

    @FXML
    TextField textSizeField;

    public GraphicSettingsController()
    {
    }

    public void populateLists()
    {
        // populate the Choicebox;
        themeChoiceBox.setItems(settingsModel.getAllThemes());
        resolutionChoiceBox.setItems(settingsModel.getAllResolutions());
        fontChoiceBox.setItems(settingsModel.getAllFonts());
    }
}
