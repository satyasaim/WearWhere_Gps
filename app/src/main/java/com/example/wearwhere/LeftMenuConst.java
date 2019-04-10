package com.example.wearwhere;

/**
 * Created by HP on 10/23/2017.
 */

public class LeftMenuConst {
    String title;
    int icon;

    public LeftMenuConst(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
