package com.pasha.entity.external.stats;

import java.io.Serializable;
import java.util.ArrayList;

public class ExtStats implements Serializable {
    private ArrayList<ExtMatchStat> data;
    private Integer filtered;
    private Integer total;
    private Integer skip;
    private Integer limit;

    public ArrayList<ExtMatchStat> getData() {
        return data;
    }

    public void setData(ArrayList<ExtMatchStat> data) {
        this.data = data;
    }

    public Integer getFiltered() {
        return filtered;
    }

    public void setFiltered(Integer filtered) {
        this.filtered = filtered;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
