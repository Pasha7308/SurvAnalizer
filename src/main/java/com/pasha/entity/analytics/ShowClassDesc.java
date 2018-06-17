package com.pasha.entity.analytics;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ShowClassDesc {
    private List<ShowFieldDesc> fields = new ArrayList<>();

    public List<ShowFieldDesc> getFields() {
        return fields;
    }

    public void setFields(List<ShowFieldDesc> fields) {
        this.fields = fields;
    }

    public void add(String title, String method, int width) {
        fields.add(new ShowFieldDesc(title, method, width));
    }
}
