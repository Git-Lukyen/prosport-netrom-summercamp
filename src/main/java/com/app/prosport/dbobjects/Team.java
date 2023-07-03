package com.app.prosport.dbobjects;

public class Team {
    private int teamID;
    private String teamName;

    public void setTeamID(int ID) {
        teamID = ID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamName(String name) {
        teamName = name;
    }

    public String getTeamName() {
        return teamName;
    }
}
