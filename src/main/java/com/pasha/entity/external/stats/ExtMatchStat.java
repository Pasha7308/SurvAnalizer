package com.pasha.entity.external.stats;

import com.pasha.entity.external.ExtContainerId;
import com.pasha.entity.external.ExtContainerTitle;
import org.joda.time.DateTime;

import java.io.Serializable;

public class ExtMatchStat implements Serializable {
    private DateTime date;
    private ExtContainerId match;
    private ExtContainerTitle battlefield;
    private ExtContainerTitle mode;
    private ExtContainerTitle weather;
    private Integer team;
    private Boolean rating_match;
    private Boolean victory;
    private Integer artefactUses;
    private Integer boxesBringed;
    private Integer pointCaptures;
    private Integer artefactKills;
    private Integer meleeKills;
    private Integer grenadeKills;
    private Integer headshots;
    private Double kd;
    private Integer dies;
    private Integer kills;
    private Integer score;
    private Integer place;
    private Integer level;

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public ExtContainerId getMatch() {
        return match;
    }

    public void setMatch(ExtContainerId match) {
        this.match = match;
    }

    public ExtContainerTitle getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(ExtContainerTitle battlefield) {
        this.battlefield = battlefield;
    }

    public ExtContainerTitle getMode() {
        return mode;
    }

    public void setMode(ExtContainerTitle mode) {
        this.mode = mode;
    }

    public ExtContainerTitle getWeather() {
        return weather;
    }

    public void setWeather(ExtContainerTitle weather) {
        this.weather = weather;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Boolean getRating_match() {
        return rating_match;
    }

    public void setRating_match(Boolean rating_match) {
        this.rating_match = rating_match;
    }

    public Boolean getVictory() {
        return victory;
    }

    public void setVictory(Boolean victory) {
        this.victory = victory;
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

    public Double getKd() {
        return kd;
    }

    public void setKd(Double kd) {
        this.kd = kd;
    }

    public Integer getDies() {
        return dies;
    }

    public void setDies(Integer dies) {
        this.dies = dies;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
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
