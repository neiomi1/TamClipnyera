package main.java.application.detailedItem;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.application.model.ClipContainer;

public class DetailView extends VBox
{

    private DetailPresenter detailPresenter;

    private Label title;

    private Text detail;

    private String[] tags;

    private Long id;

    public DetailView()
    {
        initView();
    }

    public void initView()
    {
        setSpacing(10);

        title = new Label();
        detail = new Text();

        getChildren().addAll(title, detail);

        HBox buttons = new HBox();
        Button save = new Button("Save");
        save.setOnAction(e -> detailPresenter.save());
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> detailPresenter.cancel());
        Button copy = new Button("Copy");
        copy.setOnAction(e -> detailPresenter.copy());
        buttons.getChildren().addAll(save, cancel, copy);
        getChildren().addAll(buttons);
    }

    public void setPresenter(DetailPresenter detailPresenter)
    {
        this.detailPresenter = detailPresenter;
    }

    public ClipContainer getClipContainer()
    {
        return new ClipContainer(this.id, this.title.getText().trim(), this.detail.getText().trim(), this.tags);
    }

    public void showClipContainer(ClipContainer clipContainer)
    {
        if (clipContainer != null)
        {
            this.id = clipContainer.getId();
            this.title.setText(clipContainer.getName());
            this.detail.setText(clipContainer.getDescription());
            this.tags = clipContainer.getTags();
        }
        else
        {
            this.id = (long) 0;
            this.title.setText("");
            this.detail.setText("");
            this.tags = null;
        }
    }

}
