package main.java.application.addItem;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddView extends GridPane
{
    private AddPresenter addPresenter;

    private Label intro;

    private Label name;

    private TextField nameInput;

    private Label description;

    private TextField descriptionInput;

    private Label tags;

    private TextField tagsInput;

    private ComboBox<String> possibleFiles;

    private Button save;

    private Button saveAll;

    private Button back;

    public AddView()
    {
    }

    public void init(ObservableList<String> fileNames)
    {
        intro = new Label("Adding new Entry");
        name = new Label("Name:");
        nameInput = new TextField();
        nameInput.setPromptText("This will show up and be copied");
        description = new Label("Description: ");
        descriptionInput = new TextField();
        descriptionInput.setPromptText("Optional - This will show up in dictionary mode");
        possibleFiles = new ComboBox<String>(fileNames);
        possibleFiles.getSelectionModel().selectLast();
        tags = new Label("Tags: ");
        tagsInput = new TextField();
        tagsInput.setPromptText("Optional - Any number of tags for the item separated by ','");

        save = new Button("save");
        save.setOnAction(e -> addPresenter.save(nameInput.getText(), descriptionInput.getText(), tagsInput.getText().split(","), possibleFiles.getSelectionModel().getSelectedItem()));
        saveAll = new Button("save All");
        saveAll.setOnAction(e -> addPresenter.saveAll(nameInput.getText(), descriptionInput.getText(), tagsInput.getText().split(","), possibleFiles.getSelectionModel().getSelectedItem()));
        back = new Button("back");
        back.setOnAction(e ->
        {
            addPresenter.back();
            clearEntries();
        });

        add(intro, 0, 0);
        add(name, 0, 1);
        add(nameInput, 1, 1);
        add(description, 0, 2);
        add(descriptionInput, 1, 2);
        add(possibleFiles, 0, 3);
        add(tags, 0, 4);
        add(tagsInput, 1, 4);
        add(save, 0, 5);
        add(saveAll, 1, 5);
        add(back, 2, 5);

    }

    public void setAddPresenter(AddPresenter addPresenter)
    {
        this.addPresenter = addPresenter;
    }

    public void clearEntries()
    {
        nameInput.clear();
        descriptionInput.clear();
        tagsInput.clear();
    }
}
