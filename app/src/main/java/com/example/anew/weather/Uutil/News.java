package com.example.anew.weather.Uutil;

import java.util.List;

/**
 * Created by new on 2019/11/25.
 */

public class News {
    private String stat;
    private List<Data> data;

    public News(String stata, List<Data> list) {
        this.stat = stata;
        this.data = list;
    }

    public String getStata() {
        return stat;
    }

    public void setStata(String stata) {
        this.stat= stata;
    }

    public List<Data> getList() {
        return data;
    }

    public void setList(List<Data> list) {
        this.data = list;
    }
}
