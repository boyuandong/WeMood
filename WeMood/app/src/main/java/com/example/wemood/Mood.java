package com.example.wemood;

public class Mood {
     private String date, time, emotionalState, comment, sotialSituation, location;

    public Mood(String date, String time, String emotionalState, String comment, String sotialSituation, String location) {
        this.date = date;
        this.time = time;
        this.emotionalState = emotionalState;
        this.comment = comment;
        this.sotialSituation = sotialSituation;
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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
}
