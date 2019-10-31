package com.example.wemood;

import java.util.ArrayList;

public interface MoodList {
    ArrayList<Mood> allMoods();

    void addMood(Mood mood);

    void removeMood(Mood mood);
}
