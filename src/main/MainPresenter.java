package main;

import add.AddPresenter;
import detail.DetailPresenter;
import javafx.collections.ObservableList;
import model.ClipContainer;
import model.ClipContainerModel;
import overview.OverviewPresenter;
import settings.SettingsPresenter;

public class MainPresenter
{
    private MainView mainView;

    private OverviewPresenter overviewPresenter;

    private DetailPresenter detailPresenter;

    private SettingsPresenter settingsPresenter;

    private String currentKey;

    private String lastView;

    private ClipContainer container;

    private String lastUser;

    private AddPresenter addPresenter;

    public MainPresenter()
    {
    }

    public void setSettingsPresenter(SettingsPresenter settingsPresenter)
    {
        this.settingsPresenter = settingsPresenter;
    }

    public void setView(MainView mainView)
    {
        this.mainView = mainView;
    }

    public MainView getMainView()
    {
        lastUser = settingsPresenter.getStartingUser();
        mainView.updateComboBox(settingsPresenter.getAllUsers(), lastUser);
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

    public void setAddPresenter(AddPresenter addPresenter) {
        this.addPresenter = addPresenter;
    }
    public void showOverviewView()
    {
        lastView = "overview";
        overviewPresenter.search();
        mainView.setContent(overviewPresenter.getView());
    }

    public void showDetailView(ClipContainer container, String key)
    {
        lastView = "detail";
        this.container = container;
        currentKey = key;
        detailPresenter.setClipContainer(container);
        mainView.setContent(detailPresenter.getView());
    }

    public String getCurrentKey()
    {
        return currentKey;
    }

    public void showSettingsView()
    {
        mainView.setTopContent(settingsPresenter.getTop());
        mainView.setContent(settingsPresenter.getView());
    }

    public void showLastView()
    {
        mainView.initView();
        mainView.updateComboBox(settingsPresenter.getAllUsers(), lastUser);
        if (lastView.contentEquals("detail") && !getMode().contentEquals("copy"))
        {
            showDetailView(this.container, currentKey);
        }
        else
        {
            showOverviewView();
        }
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
