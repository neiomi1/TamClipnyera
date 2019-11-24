package overview;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import main.MainPresenter;
import model.ClipContainer;
import model.ClipContainerMap;
import model.ClipContainerModel;

public class OverviewPresenter
{
    private OverviewView view;

    private MainPresenter mainPresenter;

    private ClipContainerModel containerModel;

    private ClipContainerModel clipContainerModel;

    private StringSelection stringSelection;

    private String copyToClipboard;

    private Clipboard clipboard;

    public OverviewPresenter()
    {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void setView(OverviewView view)
    {
        this.view = view;
    }

    public OverviewView getView()
    {
        return this.view;
    }

    public void setMainPresenter(MainPresenter presenter)
    {
        this.mainPresenter = presenter;
    }

    public void setClipContainerModel(ClipContainerModel model)
    {
        this.containerModel = model;
    }

    public void search()
    {
        String searchPhrase = view.getSearchPhrase();
        final String[] keywords = searchPhrase.split("\\s+");
        List<ClipContainerMap> hits = containerModel.searchClipContainers(keywords);
        System.out.println("****************************************************");
        for (ClipContainerMap map : hits)
        {
            System.out.println(map.getCsvName());
            for (ClipContainer elem : map.getStoredMap().values())
            {
                System.out.println(elem.getId());
                System.out.println(elem.getName());
                System.out.println(elem.getDescription());
            }
        }
        view.showSearchResults(hits);
    }

    public void ClipContainerSelected(ClipContainer clipContainer, String key)
    {
        mainPresenter.showDetailView(clipContainer, key);
    }

    public boolean checkCopyMode()
    {
        if (mainPresenter.getMode().contentEquals("copy"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void copyToClipboard(ClipContainer c)
    {
        copyToClipboard = c.getName();
        stringSelection = new StringSelection(copyToClipboard);
        clipboard.setContents(stringSelection, null);
    }

    public void addEntry() {
        mainPresenter.showAddView();
    }
}
