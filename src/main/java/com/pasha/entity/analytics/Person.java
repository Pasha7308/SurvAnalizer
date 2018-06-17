package com.pasha.entity.analytics;

public class Person {

    private PersonStats solo = new PersonStats();
    private PersonStats group = new PersonStats();

    public PersonStats getSolo() {
        return solo;
    }

    public void setSolo(PersonStats solo) {
        this.solo = solo;
    }

    public PersonStats getGroup() {
        return group;
    }

    public void setGroup(PersonStats group) {
        this.group = group;
    }

    public String getKills(boolean isSolo) {
        return String.format("%.2f", isSolo ? solo.getAverageKills() : group.getAverageKills());
    }

    public String getDeaths(boolean isSolo) {
        return String.format("%.2f", isSolo ? solo.getAverageDeaths() : group.getAverageDeaths());
    }

    public String getKDs(boolean isSolo) {
        return String.format("%.2f", isSolo ? solo.getAverageKDs() : group.getAverageKDs());
    }

    public int getMatchCount(boolean isSolo) {
        return isSolo ? solo.getMatchCount() : group.getMatchCount();
    }

    public void divide()
    {
        solo.divide();
        group.divide();
    }
}
