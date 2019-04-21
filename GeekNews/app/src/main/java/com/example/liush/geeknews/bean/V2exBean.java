package com.example.liush.geeknews.bean;

/**
 * Created by liush on 2019/4/21.
 */

public class V2exBean {
    private String time;
    private String author;
    private String title;
    private String secondtab;
    private String count;
    private String img;


    public V2exBean(String time, String author, String title, String secondtab, String count, String img) {
        this.time = time;
        this.author = author;
        this.title = title;
        this.secondtab = secondtab;
        this.count = count;
        this.img = img;
    }

    public V2exBean() {
    }

    @Override
    public String toString() {
        return "V2exBean{" +
                "time='" + time + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", secondtab='" + secondtab + '\'' +
                ", count='" + count + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSecondtab(String secondtab) {
        this.secondtab = secondtab;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getSecondtab() {
        return secondtab;
    }

    public String getCount() {
        return count;
    }

    public String getImg() {
        return img;
    }
}
