package main.java.application.settings;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.java.controllers.MainSettingsController;

public class SettingsView extends VBox
{
    private MainSettingsController settingsPresenter;

    private ObservableList<String> allCharacters;

    private ComboBox<String> loadedCharacter;

    private String selectedCharacter;

    private ToggleGroup mode;

    private RadioButton copy;

    private RadioButton dictionary;

    private String selectedMode;

    public SettingsView()
    {
        init();
    }

    public void init()
    {
        setPadding(new Insets(10, 10, 10, 10));
        loadedCharacter = new ComboBox<String>(allCharacters);
        loadedCharacter.valueProperty().addListener((p, o, n) -> this.selectedCharacter = n);
        loadedCharacter.getSelectionModel().select(this.selectedCharacter);
        this.mode = new ToggleGroup();
        copy = new RadioButton("Copy selected name to clipboard");
        copy.setOnAction(e -> selectedMode = "copy");
        dictionary = new RadioButton("Open additional information of selected");
        dictionary.setOnAction(e -> selectedMode = "dictionary");
        copy.setToggleGroup(this.mode);
        dictionary.setToggleGroup(this.mode);

        HBox buttons = new HBox();
        Button save = new Button("save");
        Button back = new Button("back");
        buttons.getChildren().addAll(save, back);
        save.setOnAction(e -> settingsPresenter.updateSettings());
        back.setOnAction(e -> settingsPresenter.back());
        getChildren().addAll(new Label("Characters"), loadedCharacter, new Label("Mode"), copy, dictionary, buttons);
    }

    public void updateInfo(ObservableList<String> allCharacters, String selectedCharacter, String mode)
    {
        this.allCharacters = allCharacters;
        getChildren().remove(1);
        loadedCharacter = new ComboBox<String>(allCharacters);
        loadedCharacter.valueProperty().addListener((p, o, n) -> this.selectedCharacter = n);
        this.selectedCharacter = selectedCharacter;
        getChildren().add(1, loadedCharacter);
        loadedCharacter.getSelectionModel().select(this.selectedCharacter);
        this.selectedMode = mode;
        if (selectedMode.contentEquals("copy"))
        {
            copy.setSelected(true);
        }
        else
        {
            dictionary.setSelected(true);
        }
    }

    public void setSettingsPresenter(MainSettingsController settingsPresenter)
    {
        this.settingsPresenter = settingsPresenter;
    }

    public Pane getTop()
    {
        VBox temp = new VBox();
        temp.getChildren().add(new Label());
        return temp;
    }

    public List<String> getAllUsers()
    {
        System.out.println(allCharacters.stream().collect(Collectors.toList()));
        return allCharacters.stream().collect(Collectors.toList());
    }

    public String getStartingUser()
    {
        return selectedCharacter;
    }

    public String getMode()
    {
        return selectedMode;
    }
}
