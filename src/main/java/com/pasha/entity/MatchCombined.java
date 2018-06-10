package com.pasha.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class MatchCombined implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Integer pK;

    @Column(nullable = false)
    private Integer pD;

    @Column(nullable = false)
    private Double pKD;

    @Column(nullable = false)
    private Integer dK;

    @Column(nullable = false)
    private Integer dD;

    @Column(nullable = false)
    private Double dKD;

    public MatchCombined() {
    }

    public MatchCombined(Integer pK, Integer pD, Double pKD, Integer dK, Integer dD, Double dKD) {
        this.pK = pK;
        this.pD = pD;
        this.pKD = pKD;
        this.dK = dK;
        this.dD = dD;
        this.dKD = dKD;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getpK() {
        return pK;
    }

    public void setpK(Integer pK) {
        this.pK = pK;
    }

    public Integer getpD() {
        return pD;
    }

    public void setpD(Integer pD) {
        this.pD = pD;
    }

    public Double getpKD() {
        return pKD;
    }

    public void setpKD(Double pKD) {
        this.pKD = pKD;
    }

    public Integer getdK() {
        return dK;
    }

    public void setdK(Integer dK) {
        this.dK = dK;
    }

    public Integer getdD() {
        return dD;
    }

    public void setdD(Integer dD) {
        this.dD = dD;
    }

    public Double getdKD() {
        return dKD;
    }

    public void setdKD(Double dKD) {
        this.dKD = dKD;
    }
}
