package main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
        defaultTopArea.setMinHeight(54);

        // Group grp = new Group();
        // Scene dummy = new Scene(grp);
        // grp.getChildren().add(contentArea);
        // grp.applyCss();
        // grp.layout();

        topWidth.bind(parentWidth);
        topHeight.bind(parentHeight.divide(8));

        topHeight.addListener((prop, oldVal, newVal) ->
        {
            System.out.println("-----------------------------------");
            System.out.println("topHeight change:");
            System.out.println(oldVal + " " + newVal);
            contentArea.setLayoutY((double) newVal / 2);
        });

        topWidth.addListener((prop, oldVal, newVal) ->
        {
            settings.setLayoutX((double) newVal - settings.getPrefWidth());
            characters.setLayoutX((double) newVal / 2);
            selectCharacter.setLayoutX(characters.getLayoutX() - 120);
            contentArea.setBackground(new Background(new BackgroundFill(Color.DARKRED, CornerRadii.EMPTY, Insets.EMPTY)));

            System.out.println("****************************content width*********************");
            Pane p = (Pane) contentArea.getChildren().get(0);
            contentArea.setLayoutX(((double) newVal - p.getWidth()) / 2);
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
        // getChildren().addAll(topArea, contentArea);

    }

    public void setContent(Region r)
    {
        Group grp = new Group();
        Scene dummy = new Scene(grp);
        grp.getChildren().addAll(r, contentArea);
        grp.applyCss();
        grp.layout();

        System.out.println("****************************setting content width*********************");
        System.out.println(r.getWidth());
        System.out.println(contentArea.getWidth());
        // contentWidth.bind(content.widthProperty());
        contentArea.getChildren().clear();
        contentArea.getChildren().add(r);
        getChildren().setAll(topArea, contentArea);
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
