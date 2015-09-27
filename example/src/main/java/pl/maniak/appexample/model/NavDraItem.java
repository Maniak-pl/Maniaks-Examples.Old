package pl.maniak.appexample.model;

/**
 * Created by maniak on 27.09.15.
 */
public class NavDraItem {
    private String title;
    private int image;

    public NavDraItem(String title, int image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
