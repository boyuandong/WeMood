package com.example.wemood;

import java.util.Calendar;

public class Mood implements Comparable<Mood>{
    private Calendar datetime;
    private String emotionalState, explanation, comment, sotialSituation, location, username;


    public Mood(Calendar datetime, String emotionalState, String explanation, String comment, String sotialSituation, String location, String username) {
        this.datetime = datetime;
        this.emotionalState = emotionalState;
        this.explanation = explanation;
        this.comment = comment;
        this.sotialSituation = sotialSituation;
        this.location = location;
        this.username = username;
    }

    public Calendar getDatetime() {
        return datetime;
    }

    public void setDatetime(Calendar datetime) {
        this.datetime = datetime;
    }

    public String getEmotionalState() {
        return emotionalState;
    }

    public String getComment() {
        return comment;
    }

    public String getSotialSituation() {
        return sotialSituation;
    }

    public String getLocation() {
        return location;
    }


    public void setEmotionalState(String emotionalState) {
        this.emotionalState = emotionalState;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setSotialSituation(String sotialSituation) {
        this.sotialSituation = sotialSituation;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    // Compare to the other mood's date
    // used to sort moods in moodlist by date
    @Override
    public int compareTo(Mood mood){
        return getDatetime().compareTo(mood.getDatetime());
    }
}