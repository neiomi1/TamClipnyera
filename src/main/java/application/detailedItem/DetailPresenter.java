package main.java.application.detailedItem;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import main.java.application.main.MainPresenter;
import main.java.application.model.ClipContainer;
import main.java.application.model.ClipContainerModel;

public class DetailPresenter
{
    private DetailView view;

    private main.java.application.main.MainPresenter mainPresenter;

    private ClipContainerModel clipContainerModel;

    private StringSelection stringSelection;

    private String copyToClipboard;

    private Clipboard clipboard;

    public DetailPresenter()
    {
    }

    public void setView(DetailView view)
    {
        this.view = view;
    }

    public DetailView getView()
    {
        return this.view;
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setClipContainerModel(ClipContainerModel clipContainerModel)
    {
        this.clipContainerModel = clipContainerModel;
    }

    public void setClipContainer(ClipContainer container)
    {
        view.showClipContainer(container);
    }

    public void save()
    {
        ClipContainer container = view.getClipContainer();
        clipContainerModel.updateClipContainerMap(container, mainPresenter.getCurrentKey());
    }

    public void cancel()
    {
        mainPresenter.showOverviewView();
    }

    public void copy()
    {
        copyToClipboard = view.getClipContainer().getName();
        stringSelection = new StringSelection(copyToClipboard);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
