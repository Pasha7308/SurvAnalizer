package com.pasha.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.pasha.entity.MatchType.realData;

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

    @ManyToOne
    @JoinColumn(name = "pashaId")
    private MatchPerson pasha;

    @ManyToOne
    @JoinColumn(name = "daniilId")
    private MatchPerson daniil;

    @Column(nullable = false)
    private MatchType matchType = realData;

    public MatchCombined() {
    }

    public MatchCombined(Integer extId, LocalDateTime matchDate) {
        this.extId = extId;
        this.matchDate = matchDate;
    }

    public MatchCombined(Integer extId, LocalDateTime matchDate, MatchPerson pasha, MatchPerson daniil) {
        this.extId = extId;
        this.matchDate = matchDate;
        this.pasha = pasha;
        this.daniil = daniil;
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

    public MatchPerson getDaniil() {
        return daniil;
    }

    public void setDaniil(MatchPerson daniil) {
        this.daniil = daniil;
    }

    public MatchPerson getPasha() {

        return pasha;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
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

    public String getHeader() {
        String ret = "";
        switch (matchType) {
            case realData:
                ret = getMatchDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                break;
            case analyticsSolo:
                ret = "Соло";
                break;
            case analyticsGroup:
                ret = "Группа";
                break;
        }
        return ret;
    }
}
