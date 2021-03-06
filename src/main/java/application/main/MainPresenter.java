package main.java.application.main;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.java.application.addItem.AddPresenter;
import main.java.application.detailedItem.DetailPresenter;
import main.java.application.model.ClipContainer;
import main.java.application.model.ClipContainerModel;
import main.java.application.overview.OverviewPresenter;
import main.java.application.settings.SettingsModel;
import main.java.controllers.MainSettingsController;

public class MainPresenter
{
    private VBox settingsMain;

    private MainView mainView;

    private OverviewPresenter overviewPresenter;

    private DetailPresenter detailPresenter;

    private MainSettingsController settingsPresenter;

    private String currentKey;

    private String lastView;

    private ClipContainer container;

    private String lastUser;

    private AddPresenter addPresenter;

    public MainPresenter()
    {
    }

    public void setMainSettingsController()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            System.out.println(System.getProperty("user.dir"));
            System.out.println(getClass().getResource("/main/java/application/main/MainPresenter.class"));
            System.out.println(getClass().getResource("/main/resources/fxml/settingsLayout.fxml"));
            System.out.println(getClass().getResource("/main/java/controllers/MainSettingsController.class"));
            loader.setLocation(getClass().getResource("/main/resources/fxml/settingsLayout.fxml"));
            settingsMain = (VBox) loader.load();
            System.out.println(settingsMain);
            SettingsModel model = new SettingsModel();

            this.settingsPresenter = loader.getController();
            this.settingsPresenter.setSettingsModel(model);
            this.settingsPresenter.setMainPresenter(this);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void setView(MainView mainView)
    {
        this.mainView = mainView;
    }

    public MainView getMainView()
    {
        // lastUser = settingsPresenter.getStartingUser();
        // mainView.initView(settingsPresenter.getAllUsers(), lastUser);
        return mainView;
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.overviewPresenter = overviewPresenter;
    }

    public void setDetailPresenter(DetailPresenter detailPresenter)
    {
        this.detailPresenter = detailPresenter;
    }

    public void setAddPresenter(AddPresenter addPresenter)
    {
        this.addPresenter = addPresenter;
    }

    public void showOverviewView()
    {
        if (lastUser == null)
        {
            lastUser = settingsPresenter.getStartingUser();
            mainView.initView(settingsPresenter.getAllUsers(), lastUser);
        }
        lastView = "overview";
        overviewPresenter.search();
        System.out.println(lastUser);
        System.out.println("//////////////////////Overview//////////////////");
        mainView.setContent(overviewPresenter.getView());
    }

    public void showDetailView(ClipContainer container, String key)
    {
        lastView = "detail";
        this.container = container;
        currentKey = key;
        // overviewPresenter.ClipContainerSelected(this.container,
        // this.currentKey);
        detailPresenter.setClipContainer(container);
        System.out.println("//////////////////////Detail//////////////////");
        mainView.setContent(detailPresenter.getView());
    }

    public String getCurrentKey()
    {
        return currentKey;
    }

    public void showSettingsView()
    {
        System.out.println("//////////////////////Settings//////////////////");
        mainView.setContent(settingsMain);
    }

    public void showLastView()
    {
        mainView.updateSelection(lastUser);
        if (lastView.contentEquals("detail") && !getMode().contentEquals("copy"))
        {
            showDetailView(this.container, currentKey);
        }
        else
        {
            showOverviewView();
        }
    }

    public void setViewContent(Region r)
    {
        mainView.setContent(r);
    }

    public String getMode()
    {
        return settingsPresenter.getMode();
    }

    public void switchUser(String userName)
    {
        lastUser = userName;
        ClipContainerModel m = new ClipContainerModel(userName);
        overviewPresenter.setClipContainerModel(m);
        detailPresenter.setClipContainerModel(m);
        addPresenter.setClipContainerModel(m);
        showOverviewView();
    }

    public String getUser()
    {
        return lastUser;
    }

    public ObservableList<String> getAllFiles()
    {
        return settingsPresenter.getAllFiles();
    }

    public void showAddView()
    {
        mainView.setContent(addPresenter.getView());
    }
}
