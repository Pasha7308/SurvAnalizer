package com.pasha.entity;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class MatchPerson implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Integer kills;

    @Column
    private Integer deaths;

    @Column
    private Double kd;

    @Column
    private Integer artefactUses;

    @Column
    private Integer boxesBringed;

    @Column
    private Integer pointCaptures;

    @Column
    private Integer artefactKills;

    @Column
    private Integer meleeKills;

    @Column
    private Integer grenadeKills;

    @Column
    private Integer headshots;

    @Column
    private Integer score;

    @Column
    private Integer place;

    @Column
    private Integer level;

    public MatchPerson() {
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

    public Integer getArtefactUses() {
        return artefactUses;
    }

    public void setArtefactUses(Integer artefactUses) {
        this.artefactUses = artefactUses;
    }

    public Integer getBoxesBringed() {
        return boxesBringed;
    }

    public void setBoxesBringed(Integer boxesBringed) {
        this.boxesBringed = boxesBringed;
    }

    public Integer getPointCaptures() {
        return pointCaptures;
    }

    public void setPointCaptures(Integer pointCaptures) {
        this.pointCaptures = pointCaptures;
    }

    public Integer getArtefactKills() {
        return artefactKills;
    }

    public void setArtefactKills(Integer artefactKills) {
        this.artefactKills = artefactKills;
    }

    public Integer getMeleeKills() {
        return meleeKills;
    }

    public void setMeleeKills(Integer meleeKills) {
        this.meleeKills = meleeKills;
    }

    public Integer getGrenadeKills() {
        return grenadeKills;
    }

    public void setGrenadeKills(Integer grenadeKills) {
        this.grenadeKills = grenadeKills;
    }

    public Integer getHeadshots() {
        return headshots;
    }

    public void setHeadshots(Integer headshots) {
        this.headshots = headshots;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
