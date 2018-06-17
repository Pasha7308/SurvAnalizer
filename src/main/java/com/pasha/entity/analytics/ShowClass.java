package com.pasha.entity.analytics;

public class ShowClass {
    private String description;
    private String pmatch;
    private String pk;
    private String pd;
    private String pkd;
    private String dmatch;
    private String dk;
    private String dd;
    private String dkd;

    public ShowClass() {
    }

    public ShowClass(String description, String pmatch, String pk, String pd, String pkd, String dmatch, String dk, String dd, String dkd) {
        this.description = description;
        this.pmatch = pmatch;
        this.pk = pk;
        this.pd = pd;
        this.pkd = pkd;
        this.dmatch = dmatch;
        this.dk = dk;
        this.dd = dd;
        this.dkd = dkd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public String getPkd() {
        return pkd;
    }

    public void setPkd(String pkd) {
        this.pkd = pkd;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getDkd() {
        return dkd;
    }

    public void setDkd(String dkd) {
        this.dkd = dkd;
    }

    public String getPmatch() {
        return pmatch;
    }

    public void setPmatch(String pmatch) {
        this.pmatch = pmatch;
    }

    public String getDmatch() {
        return dmatch;
    }

    public void setDmatch(String dmatch) {
        this.dmatch = dmatch;
    }
}
