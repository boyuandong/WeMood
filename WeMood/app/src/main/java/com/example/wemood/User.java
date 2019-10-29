package com.example.wemood;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name, password;
    private ArrayList<Mood> myMoodList;
    private ArrayList<Mood> friendMoodList;
    private ArrayList<User> friendList;
    private ArrayList<String>locationList;
    private ArrayList<User> requestList;
    private ArrayList<User> acceptedList;
    private ArrayList<User> refusedList;
    private ArrayList<Mood> allMoodList;


    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.myMoodList = new ArrayList<>();
        this.friendMoodList = new ArrayList<>();
        this.friendList = new ArrayList<>();
        this.locationList = new ArrayList<>();
        this.requestList = new ArrayList<>();
        this.acceptedList = new ArrayList<>();
        this.refusedList = new ArrayList<>();
        this.allMoodList = new ArrayList<>();
    }

    public ArrayList<Mood> getMyMoodList() {
        return myMoodList;
    }

    public ArrayList<Mood> getFriendMoodList() {
        return friendMoodList;
    }

    public ArrayList<String> getLocationList() {
        return locationList;
    }

    public ArrayList<Mood> getAllMoodList() {
        return allMoodList;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<User> getAcceptedList() {
        return acceptedList;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public ArrayList<User> getRequestList() {
        return requestList;
    }

    public void editMood(int index, Mood mood){
        this.myMoodList.set(index, mood);
    }

    public void addMood(Mood mood){
        this.myMoodList.add(mood);

    }

    public void deleteMood(int index){
        this.myMoodList.remove(index);
    }

    public void unfollowFriend(User friend){
        int index = 0;
        for (User user:friendList ){
            index++;
            if (friend.getName() == user.getName()){
                this.friendList.remove(index);
            }
        }
    }

    public User searchFriend(ArrayList<User> database, String name){
        for (User user:database ){
            if (user.getName() == name){
                return user;
            }
        }
        throw new IllegalArgumentException();
    }

    public ArrayList<User> getRefusedList() {
        return refusedList;
    }

    public void acceptRequest(User other, User myself){
        this.friendList.add(other);
        other.getAcceptedList().add(myself);
        other.getFriendList().add(myself);
    }

    public void refuseRequest(User other, User myself){
        other.getRefusedList().add(myself);

    }
    public ArrayList<Mood> filterMoodList(String color){
        ArrayList<Mood> happyList= new ArrayList<>();
        ArrayList<Mood> aloneList = new ArrayList<>();
        ArrayList<Mood> sadList= new ArrayList<>();
        for (Mood mood:this.myMoodList ){
            if (mood.getEmotionalState() == "alone"){
                aloneList.add(mood);
            }else if (mood.getEmotionalState() == "happy"){
                happyList.add(mood);
            }else if (mood.getEmotionalState() == "sad"){
                sadList.add(mood);
            }
        }
        if(color == "green"){
            return aloneList;
        }else if (color == "red"){
            return happyList;
        }else{
            return sadList;
        }
    }


    public void addToRequestList(User user){
        user.getRequestList().add(user);
    }
}
