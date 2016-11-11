package com.miao.android.knowledges.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */

public class InfoBean {

    private int date;
    private List<Stories> mStories;
    private List<Top_Stories> mTop_stories;

    public List<Stories> getStories() {
        return mStories;
    }

    public void setStories(List<Stories> stories) {
        mStories = stories;
    }

    public List<Top_Stories> getTop_stories() {
        return mTop_stories;
    }

    public void setTop_stories(List<Top_Stories> top_stories) {
        mTop_stories = top_stories;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public static class Stories {
        private String title;
        private List<String> images;
        private int id;
        private int ga_prefix;
        private int type;
        private boolean multipic = false;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(int ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }
    }

    public static class Top_Stories {
        private String title;
        private String image;
        private int id;
        private int ga_prefix;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(int ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
