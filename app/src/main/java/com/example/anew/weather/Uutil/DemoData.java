package com.example.anew.weather.Uutil;

/**
 * Created by new on 2019/11/18.
 */

public class DemoData {
    private String name;
    private int imageId;
    private String content;

   public DemoData(){

   }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DemoData(String name, int imageId, String content)
    {
        this.name=name;
        this.imageId=imageId;
        this.content=content;
    }
    public String getName()
    {
        return  this.name;
    }
    public int getImageId()
    {
        return  this.imageId;
    }
    public String getContent() {
        return content;
    }

}
