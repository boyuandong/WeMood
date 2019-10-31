package com.example.wemood;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MyMoodList implements MoodList{
    private static final String TAG = "MyMoodList";
    private DatabaseReference mDatabase;
    private ArrayList<Mood> myMoods = new ArrayList<>();
    private String userMame;

    public ArrayList<Mood> allMoods(){
        return myMoods;
    }

    public void addMood(Mood mood){

    }

    public void removeMood(Mood mood){

    }
}
