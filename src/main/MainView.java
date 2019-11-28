package main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MainView extends Pane
{
    private Label selectCharacter;

    private Button settings;

    private ComboBox<String> characters;

    private MainPresenter mainPresenter;

    private Pane defaultTopArea, topArea, contentArea;

    private DoubleProperty contentWidth, topWidth, topHeight;

    private ReadOnlyDoubleProperty parentWidth, parentHeight;

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public MainView()
    {

        defaultTopArea = new Pane();

        contentArea = new Pane();

        topArea = new Pane();
        settings = new Button("Settings");

        settings.setOnAction(e -> mainPresenter.showSettingsView());

        settings.setLayoutY(0);
        settings.setPrefWidth(100);

        selectCharacter = new Label("Select your Character: ");
        selectCharacter.setLayoutY(0);

        parentWidth = widthProperty();
        parentHeight = heightProperty();

        topWidth = defaultTopArea.prefWidthProperty();
        topHeight = defaultTopArea.prefHeightProperty();

        contentWidth = contentArea.prefWidthProperty();

    }

    public void initView(ObservableList<String> comboList, String lastUser)
    {

        topWidth.bind(parentWidth);
        topHeight.bind(parentHeight.divide(8));

        topHeight.addListener((prop, oldVal, newVal) ->
        {
            contentArea.setLayoutY((double) newVal / 2);
        });

        topWidth.addListener((prop, oldVal, newVal) ->
        {
            System.out.println("----------------------");
            System.out.println(newVal + " | " + contentArea.getWidth());
            System.out.println((double) newVal - contentArea.getWidth());
            System.out.println(((double) newVal - contentArea.getWidth()) / 2);
            // contentArea.prefWidthProperty().bind(parentWidth.divide(3));
            contentArea.setLayoutX(((double) newVal - contentArea.getWidth()) / 2);
            settings.setLayoutX((double) newVal - settings.getPrefWidth());
            characters.setLayoutX((double) newVal / 2);
            selectCharacter.setLayoutX(characters.getLayoutX() - 120);
            contentArea.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));

            System.out.println("****************************content width*********************");
            Pane p = (Pane) contentArea.getChildren().get(0);
            System.out.println(p.getWidth());

        });
        characters = new ComboBox<String>(comboList);
        characters.valueProperty().addListener((p, o, n) -> mainPresenter.switchUser(n));
        characters.getSelectionModel().select(lastUser);

        characters.setLayoutY(0);

        defaultTopArea.getChildren().addAll(selectCharacter, characters, settings);

        System.out.println("========================================= init ");
        System.out.println(contentArea);
        setTopContent(defaultTopArea);
        getChildren().addAll(topArea, contentArea);

        // Pane pn = (Pane) contentArea.getChildren().get(0);
        // DoubleProperty pro = pn.prefWidthProperty();
        // pro.bind(contentArea.widthProperty());
        // contentArea.prefWidth(432.0);
    }

    public void setContent(Pane content)
    {
        System.out.println("****************************content width*********************");
        System.out.println(content.getWidth());
        System.out.println(contentArea);
        contentWidth.bind(content.widthProperty());
        contentArea.getChildren().clear();
        contentArea.getChildren().add(content);
    }

    public void setTopContent(Pane content)
    {
        topArea.getChildren().clear();
        topArea.getChildren().add(content);
    }

    public void updateSelection(String user)
    {
        topArea.getChildren().clear();
        topArea.getChildren().add(defaultTopArea);
        characters.getSelectionModel().select(user);
    }
}
