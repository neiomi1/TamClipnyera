package overview;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.ClipContainer;
import model.ClipContainerMap;

public class OverviewView extends VBox
{
    private OverviewPresenter presenter;

    private TextField searchField;

    private VBox accordionView;

    private List<TitledPane> panes;

    private Button addEntry;

    public OverviewView()
    {
        initView();
    }

    public void initView()
    {
        setSpacing(10);

        HBox searchBar = new HBox(10);

        searchBar.getChildren().add(new Label("Search:"));
        searchField = new TextField();
        searchField.setPrefColumnCount(20);
        searchField.setOnAction(e -> presenter.search());
        searchBar.getChildren().add(searchField);

        Button searchButton = new Button("Suchen");
        searchButton.setOnAction(e -> presenter.search());
        searchBar.getChildren().add(searchButton);

        addEntry = new Button("Add Entry");
        addEntry.setOnAction(e -> presenter.addEntry());
        searchBar.getChildren().add(addEntry);
        getChildren().add(searchBar);

        accordionView = new VBox();
        panes = new ArrayList<>();
        ListView<ClipContainer> tempListView = new ListView<ClipContainer>();
        tempListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
        {
            if (newValue != null)
            {
                if (presenter.checkCopyMode())
                {
                    presenter.copyToClipboard(newValue);
                }
                else
                {
                    presenter.ClipContainerSelected(newValue, "Favourites");
                }
            }
        });
        BorderPane.setMargin(tempListView, new Insets(10, 10, 10, 10));
        VBox.setVgrow(tempListView, Priority.ALWAYS);
        TitledPane favourite = new TitledPane("Favourites", tempListView);
        panes.add(favourite);
        getChildren().add(accordionView);
    }

    public void setPresenter(OverviewPresenter presenter)
    {
        this.presenter = presenter;
    }

    public String getSearchPhrase()
    {
        return searchField.getText();
    }

    public void showSearchResults(List<ClipContainerMap> searchResults)
    {
        // setPanesInvisible();
        accordionView.getChildren().clear();
        System.out.println("*************************************");
        for (ClipContainerMap m : searchResults)
        {
            System.out.println(accordionView.getChildren().size());
            ListView<ClipContainer> tempListView = null;
            TitledPane tempPane = null;
            for (TitledPane pane : panes)
            {
                System.out.println(pane.getText());
                System.out.println(pane.getContent());
                if (m.getCsvName().contentEquals(pane.getText()))
                {
                    if (m.getStoredMap().values().isEmpty())
                    {
                        tempPane = null;
                        break;
                    }
                    if (pane.getText().contentEquals("Favourites"))
                    {
                        accordionView.getChildren().add(0, pane);

                    }
                    else
                    {
                        accordionView.getChildren().add(pane);
                    }
                    tempListView = (ListView<ClipContainer>) pane.getContent();
                    tempListView.getItems().setAll(m.getStoredMap().values());
                    tempListView.setPrefHeight(m.getStoredMap().values().size() * 25);
                    System.out.println(tempListView.getPrefHeight());
                    tempPane = null;
                    System.out.println("Hit");
                    break;
                }
                else
                {
                    if (tempPane == null)
                    {
                        System.out.println("Else case" + m.getCsvName());
                        tempListView = new ListView<ClipContainer>();
                        tempListView.getItems().setAll(m.getStoredMap().values());
                        tempListView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
                        {
                            if (presenter.checkCopyMode())
                            {
                                presenter.copyToClipboard(newValue);
                            }
                            else
                            {
                                presenter.ClipContainerSelected(newValue, m.getCsvName());
                            }
                        });
                        BorderPane.setMargin(tempListView, new Insets(10, 10, 10, 10));
                        VBox.setVgrow(tempListView, Priority.ALWAYS);
                        tempPane = new TitledPane(m.getCsvName(), tempListView);
                        tempPane.setManaged(true);
                    }
                }
            }
            if (tempPane != null)
            {
                System.out.println("Adding PANE:" + tempPane.getText() + tempPane.getContent());
                System.out.println("----------------");
                panes.add(tempPane);
                accordionView.getChildren().add(tempPane);
            }
        }
    }

}
