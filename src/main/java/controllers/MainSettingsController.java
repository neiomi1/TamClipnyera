package main.java.controllers;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.application.settings.SettingsView;

public class MainSettingsController extends AbstractController
{

    private GridPane graphicSettings;

    private GridPane functionalitySettings;

    private ScrollPane characterSettings;

    private SettingsView settingsView;

    public MainSettingsController()
    {
        System.err.println(this);
    }

    @Override
    @FXML
    public void back()
    {

        mainPresenter.showLastView();
    }

    public Pane getTop()
    {
        return new VBox();
        // return this.settingsView.getTop();
    }

    public String getMode()
    {
        return settingsModel.getMode();
    }

    public void updateSettings()
    {
        settingsModel.updateModel(settingsView.getAllUsers(), settingsView.getStartingUser(), settingsView.getMode());
    }

    public ObservableList<String> getAllUsers()
    {
        return settingsModel.getAllUsersProperty();
    }

    public String getStartingUser()
    {
        return settingsModel.getStartingUser();
    }

    public ObservableList<String> getAllFiles()
    {
        return settingsModel.getAllFilesProperty();
    }

    @FXML
    public void showGraphicSettings()
    {
        System.out.println("Showing graphic settings");
        if (graphicSettings == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                System.err.println(getClass().getResource("GraphicSettings.fxml"));
                System.err.println(getClass().getResource("/GraphicSettings.fxml"));
                loader.setLocation(getClass().getResource("/main/resources/fxml/GraphicSettings.fxml"));
                System.err.println(loader.getLocation());
                graphicSettings = (GridPane) loader.load();

                GraphicSettingsController graphicSettingsController = loader.getController();
                graphicSettingsController.setSettingsModel(settingsModel);
                graphicSettingsController.setMainPresenter(mainPresenter);
                graphicSettingsController.populateLists();
                System.err.println(graphicSettings);
            }
            catch (IOException e)
            {
                System.err.println("GraphicSettings.fxml not found");
                e.printStackTrace();
            }
        }
        mainPresenter.setViewContent(graphicSettings);
    }

    @FXML
    public void showFunctionalitySettings()
    {
        if (functionalitySettings == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/main/resources/fxml/Functionality.fxml"));
                System.err.println(loader.getLocation());
                functionalitySettings = (GridPane) loader.load();

                FunctionalitySettingsController functionalitySettingsController = loader.getController();
                functionalitySettingsController.setSettingsModel(settingsModel);
                functionalitySettingsController.setMainPresenter(mainPresenter);
            }
            catch (IOException e)
            {
                System.err.println("Functionality.fxml not found");
                e.printStackTrace();
            }
        }
        mainPresenter.setViewContent(functionalitySettings);
    }

    @FXML
    public void showCharacterSettings()
    {
        if (characterSettings == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/main/resources/fxml/CharacterSettings.fxml"));
                characterSettings = (ScrollPane) loader.load();

                CharacterSettingsController characterSettingsController = loader.getController();
                characterSettingsController.setSettingsModel(settingsModel);
                characterSettingsController.setMainPresenter(mainPresenter);
            }
            catch (IOException e)
            {
                System.err.println("CharacterSettings.fxml not found");
                e.printStackTrace();
            }
        }
        mainPresenter.setViewContent(characterSettings);
    }
}
