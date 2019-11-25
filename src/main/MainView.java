package main;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView extends BorderPane
{
    private Button settings;

    private ComboBox<String> characters;

    private MainPresenter mainPresenter;

    private HBox defaultTopArea;

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public MainView()
    {
    }

    public void initView(ObservableList<String> comboList, String lastUser)
    {
        defaultTopArea = new HBox(5);
        characters = new ComboBox<String>(comboList);
        characters.valueProperty().addListener((p, o, n) -> mainPresenter.switchUser(n));
        characters.getSelectionModel().select(lastUser);
        settings = new Button("Settings");

        settings.setOnAction(e -> mainPresenter.showSettingsView());
        defaultTopArea.getChildren().addAll(characters, settings);
        setTop(defaultTopArea);

    }

    public void setContent(Pane content)
    {
        setCenter(content);
        setMargin(content, new Insets(20, 20, 20, 20));
    }

    public void setTopContent(Pane content)
    {
        setTop(content);
    }

    public void updateSelection(String user)
    {
        setTop(defaultTopArea);
        characters.getSelectionModel().select(user);
    }
}
