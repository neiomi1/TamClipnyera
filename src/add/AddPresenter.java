package add;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import main.MainPresenter;
import model.ClipContainer;
import model.ClipContainerModel;
import model.ClipContainerToCSV;

public class AddPresenter
{

    private MainPresenter mainPresenter;

    private AddView addView;

    private ClipContainerModel clipContainerModel;

    public AddPresenter()
    {

    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setAddView(AddView addView)
    {
        this.addView = addView;
        ObservableList<String> test = mainPresenter.getAllFiles();
        test.add(mainPresenter.getUser());
        this.addView.init(test);
    }

    public Pane getView()
    {
        ObservableList<String> test = mainPresenter.getAllFiles();
        test.add("Favourites");
        this.addView.updateComboBox(test);
        return this.addView;
    }

    public void setClipContainerModel(ClipContainerModel clipContainerModel)
    {
        this.clipContainerModel = clipContainerModel;
    }

    public void save(String name, String description, String[] tags, String filename)
    {
        String[] temp = new String[tags.length + 3];
        temp[0] = name;
        temp[1] = description;
        temp[2] = filename;
        int i = 0;
        for (String s : tags)
        {
            temp[i + 3] = s;
            i++;
        }
        ClipContainerToCSV.writeToCSV(temp, mainPresenter.getUser());

    }

    public void saveAll(String name, String description, String[] tags, String filename)
    {
        ClipContainer container = new ClipContainer(clipContainerModel.getNextID(filename), name, description, tags);
        if (filename.contentEquals("Favourites"))
        {
            save(name, description, tags, filename);
        }
        else
        {
            ClipContainerToCSV.writeToCSV(container.toStringArray(), filename);
        }
    }

    public void back()
    {
        mainPresenter.showLastView();
    }

}
