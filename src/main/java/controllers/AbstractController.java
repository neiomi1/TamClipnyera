package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.java.application.main.MainPresenter;
import main.java.application.settings.SettingsModel;

public abstract class AbstractController
{

    protected main.java.application.main.MainPresenter mainPresenter;

    protected SettingsModel settingsModel;

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setSettingsModel(SettingsModel settingsModel)
    {
        this.settingsModel = settingsModel;
    }

    @FXML
    public void highlightText(MouseEvent e)
    {
        System.out.println("highlighting");
        Label source = (Label) e.getSource();
        source.setTextFill(Color.ALICEBLUE);
    }

    @FXML
    public void dehighlightText(MouseEvent e)
    {
        System.out.println("dehighlighting");
        Label source = (Label) e.getSource();
        source.setTextFill(Color.BLACK);
    }

    @FXML
    public void back()
    {
        mainPresenter.showSettingsView();
    }

}
