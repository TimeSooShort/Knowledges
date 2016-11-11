package com.miao.android.knowledges.bean;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HotNewsBean {

    private String news_id;
    private String thumbnail;
    private String title;

    public HotNewsBean(String news_id, String thumbnail, String title) {
        this.news_id = news_id;
        this.thumbnail = thumbnail;
        this.title = title;
    }

    public String getNews_id() {
        return news_id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

}
