package com.pasha.entity.analytics;

public class PersonStats {

    private double averageKills = 0.0;
    private double averageDeaths = 0.0;
    private double averageKDs = 0.0;

    private int matchCount = 0;

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public double getAverageKills() {
        return averageKills;
    }

    public void setAverageKills(double averageKills) {
        this.averageKills = averageKills;
    }

    public double getAverageDeaths() {
        return averageDeaths;
    }

    public void setAverageDeaths(double averageDeaths) {
        this.averageDeaths = averageDeaths;
    }

    public double getAverageKDs() {
        return averageKDs;
    }

    public void setAverageKDs(double averageKDs) {
        this.averageKDs = averageKDs;
    }

    public void addMatch(int k, int d) {
        averageKills += k;
        averageDeaths += d;
        matchCount++;
    }

    public void divide() {
        averageKills = averageKills / matchCount;
        averageDeaths = averageDeaths / matchCount;
        averageKDs = averageKills / averageDeaths;
    }
}
