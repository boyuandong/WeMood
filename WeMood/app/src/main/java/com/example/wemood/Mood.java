package com.example.wemood;

public class Mood {
    private String date;
    private String time;
    private String emotionalState;
    private String comment;
    private String socialSituation;
    private MoodLocation location;

    public Mood(String date, String time, String emotionalState, String comment, String socialSituation, MoodLocation location) {
        this.date = date;
        this.time = time;
        this.emotionalState = emotionalState;
        this.comment = comment;
        this.socialSituation = socialSituation;
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmotionalState() {
        return emotionalState;
    }

    public void setEmotionalState(String emotionalState) {
        this.emotionalState = emotionalState;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSocialSituation() {
        return socialSituation;
    }

    public void setSocialSituation(String socialSituation) {
        this.socialSituation = socialSituation;
    }

    public MoodLocation getLocation() {
        return location;
    }

    public void setLocation(MoodLocation location) {
        this.location = location;
    }
}