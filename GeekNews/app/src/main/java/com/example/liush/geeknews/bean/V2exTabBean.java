package com.example.liush.geeknews.bean;

/**
 * Created by liush on 2019/4/21.
 */

public class V2exTabBean {
    public String link;
    public String tab;

    public V2exTabBean(String link, String tab) {
        this.link = link;
        this.tab = tab;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "link='" + link + '\'' +
                ", tab='" + tab + '\'' +
                '}';
    }
}
