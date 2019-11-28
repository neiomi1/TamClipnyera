package application;

import add.AddPresenter;
import add.AddView;
import detail.DetailPresenter;
import detail.DetailView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainPresenter;
import main.MainView;
import model.ClipContainerModel;
import overview.OverviewPresenter;
import overview.OverviewView;
import settings.SettingsModel;
import settings.SettingsPresenter;
import settings.SettingsView;

public class Main extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        MainPresenter mainPresenter = initApplication();
        mainPresenter.showOverviewView();
        Pane pane = mainPresenter.getMainView();

        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Tam Clip");
        stage.show();
    }

    private MainPresenter initApplication()
    {
        SettingsModel settingsModel = new SettingsModel();
        SettingsPresenter settingsPresenter = new SettingsPresenter();
        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView = new MainView();
        OverviewPresenter overviewPresenter = new OverviewPresenter();
        OverviewView overviewView = new OverviewView();
        DetailPresenter detailPresenter = new DetailPresenter();
        DetailView detailView = new DetailView();
        ClipContainerModel model = new ClipContainerModel(settingsModel.getStartingUser());
        SettingsView settingsView = new SettingsView();
        AddPresenter addPresenter = new AddPresenter();
        AddView addView = new AddView();

        mainPresenter.setView(mainView);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setDetailPresenter(detailPresenter);
        mainPresenter.setSettingsPresenter(settingsPresenter);
        mainPresenter.setAddPresenter(addPresenter);
        mainView.setMainPresenter(mainPresenter);

        overviewPresenter.setView(overviewView);
        overviewPresenter.setMainPresenter(mainPresenter);
        overviewPresenter.setClipContainerModel(model);
        overviewView.setPresenter(overviewPresenter);

        detailPresenter.setView(detailView);
        detailPresenter.setMainPresenter(mainPresenter);
        detailPresenter.setClipContainerModel(model);
        detailView.setPresenter(detailPresenter);

        settingsPresenter.setMainPresenter(mainPresenter);
        settingsPresenter.setSettingsModel(settingsModel);
        settingsPresenter.setSettingsView(settingsView);
        settingsView.setSettingsPresenter(settingsPresenter);

        addPresenter.setMainPresenter(mainPresenter);
        addPresenter.setClipContainerModel(model);
        addPresenter.setAddView(addView);
        addView.setAddPresenter(addPresenter);

        return mainPresenter;
    }

    @Override
    public void stop()
    {
        // TODO save files;
        System.out.println("Closed");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
