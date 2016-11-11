package com.miao.android.knowledges.bean;

/**
 * Created by Administrator on 2016/10/11.
 */

public class TopStoriesBean {

    private String title;
    private String image;
    private int id;

    public TopStoriesBean(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
