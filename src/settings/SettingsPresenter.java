package settings;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import main.MainPresenter;

public class SettingsPresenter
{
    private SettingsModel settingsModel;

    private MainPresenter mainPresenter;

    private SettingsView settingsView;

    public SettingsPresenter()
    {

    }

    public void setSettingsModel(SettingsModel settingsModel)
    {
        this.settingsModel = settingsModel;
    }

    public void setSettingsView(SettingsView settingsView)
    {
        this.settingsView = settingsView;
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public SettingsView getView()
    {
        settingsView.updateInfo(settingsModel.getAllUsersProperty(), settingsModel.getStartingUser(), settingsModel.getMode());
        return this.settingsView;
    }

    public void back()
    {

        mainPresenter.showLastView();
    }

    public Pane getTop()
    {
        return this.settingsView.getTop();
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
    
    public ObservableList<String> getAllFiles(){
        return settingsModel.getAllFilesProperty();
    }
}
