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

    public int getKills(boolean isSolo) {
        return isSolo ? (int)Math.round(solo.getAverageKills()) : (int)Math.round(group.getAverageKills());
    }

    public int getDeaths(boolean isSolo) {
        return isSolo ? (int)Math.round(solo.getAverageDeaths()) : (int)Math.round(group.getAverageDeaths());
    }

    public double getKDs(boolean isSolo) {
        return isSolo ? solo.getAverageKDs() : group.getAverageKDs();
    }

    public void divide()
    {
        solo.divide();
        group.divide();
    }
}
