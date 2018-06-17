package com.pasha.entity.analytics;

public class ShowFieldDesc {
    private String title;
    private String field;
    private int width;

    public ShowFieldDesc(String title, String field, int width) {
        this.title = title;
        this.field = field;
        this.width = width;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
