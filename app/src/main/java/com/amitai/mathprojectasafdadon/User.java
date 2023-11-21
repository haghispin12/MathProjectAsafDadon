package com.amitai.mathprojectasafdadon;

public class User {
    private String name;
    private int score;

    public User(String name){
        this.name=name;
        score=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score){
        this.score+=score;
    }
}
