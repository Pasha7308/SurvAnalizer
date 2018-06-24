package com.pasha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Column(nullable = false)
    private String battlefield;

    @ManyToOne
    @JoinColumn(name = "modeId")
    private MatchMode mode;

    @Column(nullable = false)
    private String weather;

    @Column(nullable = false)
    private Integer team;

    @Column(nullable = false)
    private Boolean rating_match;

    @Column(nullable = false)
    private Boolean victory;

    @Column(nullable = false)
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "pashaId")
    private MatchPerson pasha;

    @ManyToOne
    @JoinColumn(name = "daniilId")
    private MatchPerson daniil;

    public MatchCombined() {
    }

    public MatchCombined(Integer extId, LocalDateTime matchDate) {
        this.extId = extId;
        this.matchDate = matchDate;
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

    public String getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(String battlefield) {
        this.battlefield = battlefield;
    }

    public MatchMode getMode() {
        return mode;
    }

    public void setMode(MatchMode mode) {
        this.mode = mode;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public MatchPerson getDaniil() {
        return daniil;
    }

    public void setDaniil(MatchPerson daniil) {
        this.daniil = daniil;
    }

    public MatchPerson getPasha() {
        return pasha;
    }

    public void setPasha(MatchPerson pasha) {
        this.pasha = pasha;
    }

    public MatchPerson getPlayer(Player player) {
        MatchPerson matchPerson = null;
        switch (player) {
            case Pasha:
                matchPerson = getPasha();
                break;
            case Daniil:
                matchPerson = getDaniil();
                break;
        }
        return matchPerson;
    }

    public MatchPerson setPlayer(Player player, MatchPerson matchPerson) {
        switch (player) {
            case Pasha:
                setPasha(matchPerson);
                break;
            case Daniil:
                setDaniil(matchPerson);
                break;
        }
        return matchPerson;
    }

    public String getDate() {
        return getMatchDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
