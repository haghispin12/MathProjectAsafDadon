package com.amitai.mathprojectasafdadon;

import android.graphics.Bitmap;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Card {
private int num;
private String shape;
private boolean isHide=false;
private int rc;
public Card(){
    Random random=new Random();
    num= random.nextInt(13)+2;
    int tmp= random.nextInt(4)+1;
    if (tmp==1) {
        shape = "diamond";
        if (num==2)
            rc=R.drawable.diamond2;
        if (num==3)
            rc=R.drawable.diamond3;
        if (num==4)
            rc=R.drawable.diamond4;
        if (num==5)
            rc=R.drawable.diamond5;
        if (num==6)
            rc=R.drawable.diamond6;
        if (num==7)
            rc=R.drawable.diamond7;
        if (num==8)
            rc=R.drawable.diamond8;
        if (num==9)
            rc=R.drawable.diamond9;
        if (num==10)
            rc=R.drawable.diamond10;
        if (num==11)
            rc=R.drawable.diamond11;
        if (num==12)
            rc=R.drawable.diamond12;
        if (num==13)
            rc=R.drawable.diamond13;
        if (num==14)
            rc=R.drawable.diamond_one;
    }
    else if (tmp==2) {
        shape = "heart";
        if(num==2)
            rc=R.drawable.heart2;
        if(num==3)
            rc=R.drawable.heart3;
        if(num==4)
            rc=R.drawable.heart4;
        if(num==5)
            rc=R.drawable.heart5;
        if(num==6)
            rc=R.drawable.heart6;
        if(num==7)
            rc=R.drawable.heart7;
        if(num==8)
            rc=R.drawable.heart8;
        if(num==9)
            rc=R.drawable.heart9;
        if(num==10)
            rc=R.drawable.heart10;
        if(num==11)
            rc=R.drawable.heart11;
        if(num==12)
            rc=R.drawable.heart12;
        if(num==13)
            rc=R.drawable.heart13;
        if(num==14)
            rc=R.drawable.heart_one;
    }
    else if (tmp==3) {
        shape = "spades";
        if (num==2)
           rc=R.drawable.spades2;
        if (num==3)
            rc=R.drawable.spades3;
        if (num==4)
            rc=R.drawable.spades4;
        if (num==5)
            rc=R.drawable.spades5;
        if (num==6)
            rc=R.drawable.spades6;
        if (num==7)
            rc=R.drawable.spades7;
        if (num==8)
            rc=R.drawable.spades8;
        if (num==9)
            rc=R.drawable.spades9;
        if (num==10)
            rc=R.drawable.spades10;
        if (num==11)
            rc=R.drawable.spades11;
        if (num==12)
            rc=R.drawable.spades13;
        if (num==13)
            rc=R.drawable.spades13;
        if (num==14)
            rc=R.drawable.spades_one;
    }
    else {
        shape = "clubs";
        if (num==2)
            rc=R.drawable.clubs2;
        if (num==3)
            rc=R.drawable.clubs3;
        if (num==4)
            rc=R.drawable.clubs4;
        if (num==5)
            rc=R.drawable.clubs5;
        if (num==6)
            rc=R.drawable.clubs6;
        if (num==7)
            rc=R.drawable.clubs7;
        if (num==8)
            rc=R.drawable.clubs8;
        if (num==9)
            rc=R.drawable.clubs9;
        if (num==10)
            rc=R.drawable.clubs10;
        if (num==11)
            rc=R.drawable.clubs11;
        if (num==12)
            rc=R.drawable.clubs12;
        if (num==13)
            rc=R.drawable.clubs13;
        if (num==14)
            rc=R.drawable.clubs_one;
    }
}
public Card(int num, String shape, boolean isHide, int rc){
    this.num=num;
    this.shape=shape;
    this.isHide=isHide;
    this.rc=rc;
}

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public int getNum(){
    return num;
}

    public void setNum(int num) {
        this.num = num;
    }

    public String getShape(){
    return shape;
}

    public void setShape(String shape) {
        this.shape = shape;
    }

public boolean getIsHide(){
    return isHide;
}


    public void setIsHide(boolean hide) {
        isHide = hide;
    }
}