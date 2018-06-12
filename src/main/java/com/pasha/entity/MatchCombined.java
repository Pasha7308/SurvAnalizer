package com.pasha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class MatchCombined implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer extId;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Column(nullable = true)
    private Integer pk;

    @Column(nullable = true)
    private Integer pd;

    @Column(nullable = true)
    private Double pkd;

    @Column(nullable = true)
    private Integer dk;

    @Column(nullable = true)
    private Integer dd;

    @Column(nullable = true)
    private Double dkd;

    public MatchCombined() {
    }

    public MatchCombined(Integer extId, LocalDateTime matchDate, Integer pk, Integer pd, Double pkd, Integer dk, Integer dd, Double dkd) {
        this.extId = extId;
        this.matchDate = matchDate;
        this.pk = pk;
        this.pd = pd;
        this.pkd = pkd;
        this.dk = dk;
        this.dd = dd;
        this.dkd = dkd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDateTime matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPd() {
        return pd;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public Double getPkd() {
        return pkd;
    }

    public void setPkd(Double pkd) {
        this.pkd = pkd;
    }

    public Integer getDk() {
        return dk;
    }

    public void setDk(Integer dk) {
        this.dk = dk;
    }

    public Integer getDd() {
        return dd;
    }

    public void setDd(Integer dd) {
        this.dd = dd;
    }

    public Double getDkd() {
        return dkd;
    }

    public void setDkd(Double dkd) {
        this.dkd = dkd;
    }
}
