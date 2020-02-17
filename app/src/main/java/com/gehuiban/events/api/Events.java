package com.gehuiban.events.api;

import java.util.ArrayList;

public class Events {

    private Integer total;
    private ArrayList<Guide> data;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<Guide> getData() {
        return data;
    }

    public void setData(ArrayList<Guide> data) {
        this.data = data;
    }
}
