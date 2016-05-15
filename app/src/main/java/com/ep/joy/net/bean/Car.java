package com.ep.joy.net.bean;

import java.util.List;

/**
 * author  Joy
 * Date:  2016/5/14 0014.
 * version:  V1.0
 * Description:
 */
public class Car {


    private List<NewsListEntity> newsList;

    public List<NewsListEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsListEntity> newsList) {
        this.newsList = newsList;
    }

    public static class NewsListEntity {
        private int id;
        private String category;
        private String title;
        private String subtitle;
        private String img;
        private String releaseTime;
        private long release_time;
        private int dianjishu;
        private String interval_str;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public long getRelease_time() {
            return release_time;
        }

        public void setRelease_time(long release_time) {
            this.release_time = release_time;
        }

        public int getDianjishu() {
            return dianjishu;
        }

        public void setDianjishu(int dianjishu) {
            this.dianjishu = dianjishu;
        }

        public String getInterval_str() {
            return interval_str;
        }

        public void setInterval_str(String interval_str) {
            this.interval_str = interval_str;
        }
    }
}
