package com.example.wearwhere;

/**
 * Created by User on 5/10/2017.
 */

public class LeftMenuListConst  {
    private int imageId;
    private String title;

    //private String location;

    public  LeftMenuListConst(int imageId,String title) {
        this.title = title;
        this.imageId = imageId;

        //  this.location = location;
    }



    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /*public String getlocation() {
        return location;
    }
    public void setlocation(String location) {
        this.location = location;
    }*/
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n" + imageId;
    }

}
