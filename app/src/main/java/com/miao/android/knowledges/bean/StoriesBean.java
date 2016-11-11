package com.miao.android.knowledges.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */

public class StoriesBean {

    private String title;
    private List<String> images;
    private String id;

    public StoriesBean(String id, String title, List<String> images) {
        this.id = id;
        this.images = images;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public List<String> getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }
}
