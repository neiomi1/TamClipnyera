package main.java.application.titlescreen;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TitleView extends VBox
{
    private Button loadCharacter;

    private ComboBox<String> characters;

    private Button createCharacter;

    private Text greeting;

    public TitleView()
    {
    }

    public void init(ObservableList<String> allUsers)
    {

    }
}
