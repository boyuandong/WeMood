package com.example.wemood;

import java.util.Date;

public class Mood {
    private Date datetime;
    private String emotionalState;
    private String comment;
    private String socialSituation;


    public Mood(Date datetime, String emotionalState, String comment, String socialSituation) {
        this.datetime = datetime;
        this.emotionalState = emotionalState;
        this.comment = comment;
        this.socialSituation = socialSituation;

    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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


}