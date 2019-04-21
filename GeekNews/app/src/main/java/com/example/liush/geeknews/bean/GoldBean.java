package com.example.liush.geeknews.bean;

import java.io.Serializable;

/**
 * Created by liush on 2019/4/19.
 */

public class GoldBean implements Serializable {
    public String title;
    public boolean isChecked;

    public GoldBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
