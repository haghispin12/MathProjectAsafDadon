package com.amitai.mathprojectasafdadon;

import android.graphics.Bitmap;
import android.net.Uri;

public class User {
    private String name;
    private int score;
    private Uri uri;
    private int rate;
    private long id;
    private Bitmap bitmap;

    public User(String name){
        this.name=name;
    }

    public User(long id, String name, int rate, Bitmap bitmap, int score ) {
        this.id=id;
        this.name=name;
        this.rate=rate;
        this.bitmap=bitmap;
        this.score=score;
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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
