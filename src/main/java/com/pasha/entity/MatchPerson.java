package com.pasha.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class MatchPerson implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private Integer kills;

    @Column(nullable = true)
    private Integer deaths;

    @Column(nullable = true)
    private Double kd;

    public MatchPerson() {
    }

    public MatchPerson(Integer kills, Integer deaths, Double kd) {
        this.kills = kills;
        this.deaths = deaths;
        this.kd = kd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Double getKd() {
        return kd;
    }

    public void setKd(Double kd) {
        this.kd = kd;
    }
}
