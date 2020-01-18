package settings;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
                System.err.println(getClass().getResource("GraphicsSettings.fxml"));
                System.err.println(getClass().getResource("/GraphicSettings.fxml"));
                loader.setLocation(getClass().getResource("GraphicSettings.fxml"));
                System.err.println(loader.getLocation());
                graphicSettings = (GridPane) loader.load();
                SettingsModel model = new SettingsModel();

                GraphicSettingsController graphicSettingsController = loader.getController();
                graphicSettingsController.setSettingsModel(model);
                graphicSettingsController.setMainPresenter(mainPresenter);
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

    public void showFunctionalitySettings()
    {
        if (functionalitySettings == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainSettingsController.class.getResource("Functionality.fxml"));
                System.err.println(loader.getLocation());
                functionalitySettings = (GridPane) loader.load();
                SettingsModel model = new SettingsModel();

                FunctionalitySettingsController functionalitySettingsController = loader.getController();
                functionalitySettingsController.setSettingsModel(model);
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

    public void showCharacterSettings()
    {
        if (characterSettings == null)
        {
            try
            {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(CharacterSettingsController.class.getResource("CharacterSettings.fxml"));
                characterSettings = (ScrollPane) loader.load();
                SettingsModel model = new SettingsModel();

                CharacterSettingsController characterSettingsController = loader.getController();
                characterSettingsController.setSettingsModel(model);
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
